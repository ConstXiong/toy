package constxiong;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * 测试 java.util.Date 类
 */
public class TestDate {

    public static void main(String[] args) {
        testOldDate();
        testNewDateAndIime();
    }

    /**
     * 测试 JDK 1.8 java.time 包里的类
     */
    private static void testNewDateAndIime() {
        System.out.println("获取当前日期：" + LocalDate.now());
        //创建 2020年9月21日 日期类
        LocalDate date = LocalDate.of(2020, 9, 21);
        System.out.println("创建日期：" + date);
        System.out.println("获取年份：" + date.getYear());
        System.out.println("通过 TemporalField 接口的实现枚举类 ChronoField.YEAR 获取年份：" + date.get(ChronoField.YEAR));
        System.out.println("获取月份：" + date.getMonth().getValue());
        System.out.println("通过 TemporalField 接口的实现枚举类 ChronoField.MONTH_OF_YEAR 获取月份：" + date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("获取日：" + date.getDayOfMonth());
        System.out.println("通过 TemporalField 接口的实现枚举类 ChronoField.DAY_OF_MONTH 获取日：" + date.get(ChronoField.DAY_OF_MONTH));
        System.out.println("获取周几：" + date.getDayOfWeek());
        System.out.println("获取一个月多少天：" + date.lengthOfMonth());
        System.out.println("获取是否为闰年：" + date.isLeapYear());

        //获取当前时间
        System.out.println();
    }

    /**
     * 测试旧 java.util.Date 类
     */
    private static void testOldDate() {
        Date date = new Date();
        System.out.println(date);

        date = new Date(120, 0, 1);
        System.out.println(date);
    }
}
