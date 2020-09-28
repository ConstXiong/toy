package constxiong;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 薪水管理
 * 工资录入系统
 * 实现一个工资录入系统，给公司的HR使用。有以下功能：
 *
 * 1. 录入员工工资
 * 控制台输入：input <员工姓名> <月份> <工资>
 * 成功返回：<记录ID>_<员工姓名>_<月份>_<工资>_<记录创建时间>
 * 失败返回失败原因。
 * 例：
 * 输入：input 张三 10 5000
 * 返回：1_张三_10_5000_2016-10-10 10:00:00
 * PS：请考虑错误输入的情况。错误原因请自定义。
 *
 * 2. 列出从start开始的num条记录 按序号升序或降序返回
 * 控制台输入：list -n <Start> <Num> <asc/desc>
 * 成功返回：
 * 例：
 * 输入：list -n 0 2 desc
 * 返回：
 * 2_李四_10_6000_2016-10-10 12:00:00
 * 1_张三_10_5000_2016-10-10 10:00:00
 *
 * 3. 删除某条记录
 * 控制台输入：del <记录ID>
 * 成功返回：“成功！”
 * 失败返回原因
 */
public class TestSalaryManager {

    private static final Map<String, Command> commands = new HashMap<>();
    private static final Map<Integer, SalayInfo> map = new LinkedHashMap<>();
    private static int currentId = 0;

    static {
        commands.put("input", new Command(){
            @Override
            public void execute(String[] contents) {
                int id = getId();
                SalayInfo salayInfo = new SalayInfo(id, contents[1], Integer.parseInt(contents[2]), Double.parseDouble(contents[3]));
                map.put(id, salayInfo);
                System.out.println(salayInfo + "\n");
            }

            @Override
            public boolean validate(String[] contents) {
                if (contents.length != 4) {
                    System.out.println("参数长度错误");
                    return false;
                }
                if (!"input".equalsIgnoreCase(contents[0])) {
                    System.out.println("不支持指令");
                    return false;
                }
                //TODO 校验月份、薪资格式

                return true;
            }
        });

        commands.put("list", new Command(){

            @Override
            public void execute(String[] contents) {
                int start = Integer.parseInt(contents[2]);
                if (start < 0) {
                    start = 0;
                }
                int end = Integer.parseInt(contents[3]);
                if (end < start) {
                    System.out.println("end < start\n");
                    return;
                }
                if (start > map.size() - 1) {
                    System.out.println("start > size\n");
                }
                int index = 0;
                List<SalayInfo> result = new ArrayList<SalayInfo>();
                for (Map.Entry<Integer, SalayInfo> s : map.entrySet()) {
                    if (index>=start && index<=end) {
                        result.add(s.getValue());
                    }
                }
                if ("desc".equals(contents[4])) {
                    Collections.reverse(result);
                }
                for(SalayInfo salayInfo : result) {
                    System.out.println(salayInfo + "\n");
                }
            }

            @Override
            public boolean validate(String[] contents) {
                if (contents.length != 5) {
                    System.out.println("参数长度错误");
                    return false;
                }
                if (!"list".equalsIgnoreCase(contents[0])) {
                    System.out.println("不支持指令");
                    return false;
                }
                return true;
            }
        });
        commands.put("del", new Command(){
            @Override
            public void execute(String[] contents) {
                if (map.remove(Integer.parseInt(contents[1])) != null) {
                    System.out.println("成功\n");
                } else {
                    System.out.println("删除失败，未找到\n");
                }
            }

            @Override
            public boolean validate(String[] contents) {
                if (contents.length != 2) {
                    System.out.println("参数长度错误");
                    return false;
                }
                if (!"del".equalsIgnoreCase(contents[0])) {
                    System.out.println("不支持指令");
                    return false;
                }
                return true;
            }
        });
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = null;
        while (!"bye".equals((input = sc.nextLine()))) {
            deal(input, sc);
        }
    }

    private static int getId() {
        return ++currentId;
    }

    private static void deal(String input, Scanner sc) {
        if (input != null && input.length() > 0) {
            String[] contents = input.split(" ");
            if (contents.length >= 1) {
                String commandType = contents[0];
                Command command = commands.get(commandType);
                if (command == null) {
                    System.out.printf("不支持的指令\n");
                } else {
                    if (command.validate(contents)) {
                        command.execute(contents);
                    }
                }
            }
        }
    }

    private interface Command {
        void execute(String[] contents);
        boolean validate(String[] contents);
    }
}

class SalayInfo {
    private int id;
    private String name;
    private int month;
    private double money;
    private LocalDateTime createTime;

    public SalayInfo(int id, String name, int month, double money) {
        this.id = id;
        this.name = name;
        this.month = month;
        this.money = money;
        this.createTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return this.id + "_" + this.name + "_" + this.getMonth() + "_" + this.getMoney() + "_" + this.createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}