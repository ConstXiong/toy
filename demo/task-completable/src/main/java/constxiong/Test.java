package constxiong;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 任务拆分执行
 */
public class Test {

    private static final Random R = new Random();

    public static void main(String[] args) throws Exception {
        // 2 个查询任务
        CompletableFuture<List> cf1 = CompletableFuture.supplyAsync(Test::query);
        CompletableFuture<List> cf2 = CompletableFuture.supplyAsync(Test::query);
        success(cf1);
        success(cf2);
        failed(cf1);
        failed(cf2);
        Thread.sleep(3000L);
    }

    private static void success(CompletableFuture<List> cf) {
        new Thread(() -> {
            cf.thenAccept((result) -> {
                if (result.size() > 0){
                    ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4));
                    List<Future> futures = new ArrayList<>();
                    for (int i = 0; i < result.size(); i++) {
                        final int index = i;
                        Future<Object> future = executor.submit(() -> "subTask" + index);
                        futures.add(future);
                    }
                    for (Future f : futures) {
                        Object r = null;
                        try {
                            r = f.get();
                        } catch (Exception e) {
                        }
                        System.out.println(r);
                    }
                    executor.shutdown();
                }
            });
        }).start();
    }

    private static void failed(CompletableFuture<List> cf) {
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
    }

    private static List query() {
        List list = new ArrayList();
        int superTaks = R.nextInt(10);
        System.out.println("acquire superTaks success, num=" + superTaks);
        for (int i = 0; i < superTaks; i++) {
            list.add(i);
        }
        return list;
    }
}