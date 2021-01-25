package constxiong;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream api
 */
public class Test {

    public static void main(String[] args) {
        /*
         * 创建
         */
        // 根据集合 Collection 创建 steram、parallelStream
        List<String> list = Arrays.asList("cx1", "cx2");
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();

        // 根据数组创建 stream、parallelStream
        int[] array = {1, 2, 3};
        IntStream arrayStream = Arrays.stream(array);
        IntStream arrayParallelStream = arrayStream.parallel();

        // of 静态方法创建 stream
        Stream<Integer> ofStream = Stream.of(3, 2, 1);

        // iterate 静态方法创建 stream
        Stream<Integer> iterateStream = Stream.iterate(1, i -> i+1).limit(10);

        // generate 静态方法创建 stream
        Stream<Integer> generateStream = Stream.generate(() -> getNext()).limit(10);


        /*
         * 操作
         */
        //遍历
        iterateStream.forEach(System.out::println);

        //过滤
        generateStream.filter(i -> i % 2 == 0).forEach(System.out::println);

        //调过、截取，skip、limit
        iterateStream = Stream.iterate(1, i -> i+1).skip(3).limit(10);
        iterateStream.forEach(System.out::print);

        //找到第一个
        System.out.println(ofStream.filter(i -> i % 2 == 0).findFirst().get());

        //找到一个
        System.out.println(parallelStream.filter(name -> "cx1".equals(name)).findAny().get());

        //匹配 anyMatch
        System.out.println(Arrays.asList("cx1", "cx2").stream().anyMatch(name -> "cx1".equals(name)));
        //匹配 allMatch
        System.out.println(Arrays.asList("cx1", "cx2").stream().allMatch(name -> "cx1".equals(name)));
        //匹配 noneMatch
        System.out.println(Arrays.asList("cx1", "cx2").stream().noneMatch(name -> "cx1".equals(name)));

        //映射、收集，map、collect
        List<User> users = Arrays.asList(new User(1, "constxiong"), new User(2, "friend"));
        System.out.println(users.stream().map(User::getName).collect(Collectors.toList()));

        //映射、收集，flatMap、collect
        List<User> usersWithFSName = Arrays.asList(new User(1, "const_xiong"), new User(2, "const_friend"));
        System.out.println(usersWithFSName.stream().flatMap(u -> Arrays.stream(u.getName().split("_"))).collect(Collectors.toSet()));

        //排序
        Arrays.asList("cx2", "cx1").stream().sorted().forEach(System.out::println);
        Arrays.asList("cx2", "cx1").stream().sorted((name1, name2) -> name1.compareTo(name2) * -1).forEach(System.out::println);

        //去重
        Arrays.asList("cx2", "cx1", "cx1").stream().distinct().forEach(System.out::println);

        //求和
        System.out.println(usersWithFSName.stream().mapToDouble(User::getId).sum());

        //统计分析
        DoubleSummaryStatistics statistics = usersWithFSName.stream().mapToDouble(User::getId).summaryStatistics();
        System.out.println(statistics.getMin());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getSum());

        //归集 toSet、toList ...
        System.out.println(Arrays.asList(new User(1, "constxiong"), new User(2, "friend")).stream().map(User::getName).collect(Collectors.toSet()));
        //合并 joining
        System.out.println(Arrays.asList(new User(1, "constxiong"), new User(2, "friend")).stream().map(User::getName).collect(Collectors.joining(", ")));

        //分组 groupingBy，分为多个Map
        System.out.println(Arrays.asList(new User(1, "constxiong"), new User(2, "other"), new User(3, "friend"), new User(4, "friend")).stream()
                .collect(Collectors.groupingBy(User::getName)));

        //分区 partitioningBy，分为两个Map
        System.out.println(Arrays.asList(new User(1, "constxiong"), new User(2, "other"), new User(3, "friend"), new User(4, "friend")).stream()
                .collect(Collectors.partitioningBy(user -> user.getId() > 2)));

        //规约 reduce,和、最大、乘积
        System.out.println(Stream.iterate(1, i -> i+1).limit(10).reduce(Integer::sum).get());
        System.out.println(Stream.iterate(1, i -> i+1).limit(10).reduce(Integer::max).get());
        System.out.println(Stream.iterate(1, i -> i+1).limit(10).reduce((m, n) -> m*n).get());
    }

    static int i = 0;
    static int getNext() {
        return ++i;
    }
    static class User {
        Integer id;
        String name;
        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
        public Integer getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
