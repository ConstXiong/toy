package constxiong;


import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

        LocalTime nowTime = LocalTime.now();
        System.out.println("当前时间：" + nowTime);
        System.out.println("创建时间：" + LocalTime.of(23, 59, 59));
        System.out.println("获取时：" + nowTime.getHour());
        System.out.println("获取分：" + nowTime.getMinute());
        System.out.println("获取秒：" + nowTime.getSecond());
        System.out.println("获取毫秒：" + nowTime.getLong(ChronoField.MILLI_OF_SECOND));
        System.out.println("获取纳秒：" + nowTime.getNano());
        
        System.out.println("创建日期时间对象：" + LocalDateTime.of(2020, 9, 21, 1, 2, 3));
        System.out.println("创建日期时间对象：" + LocalDateTime.of(date, nowTime));
        System.out.println("当前日期时间对象：" + LocalDateTime.now());
        System.out.println("通过 LocalDate 创建日期时间对象：" + date.atTime(1, 2, 3));
        System.out.println("通过 LocalTime 创建日期时间对象：" + nowTime.atDate(date));
        System.out.println("通过 LocalDateTime 获取 LocalDate 对象：" + LocalDateTime.now().toLocalDate());
        System.out.println("通过 LocalDateTime 获取 LocalTime 对象：" + LocalDateTime.now().toLocalTime());
        
        //解析错误会抛出 DateTimeParseException 异常
        System.out.println("解析日期字符串：" + LocalDate.parse("2020-09-21"));
        System.out.println("解析时间字符串：" + LocalTime.parse("01:02:03"));
        System.out.println("解析日期时间字符串：" + LocalDateTime.parse("2020-09-21T01:02:03", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        //方便时间建模、机器处理的时间处理类 Instant,其实时间 1970-01-01 00:00:00
        System.out.println("起始时间 + 3 秒：" + Instant.ofEpochSecond(3));
        System.out.println("起始时间 + 3 秒 + 100 万纳秒：" + Instant.ofEpochSecond(3, 1_000_000_000));
        System.out.println("起始时间 + 3 秒 - 100 万纳秒：" + Instant.ofEpochSecond(3, -1_000_000_000));
        System.out.println("距离 1970-01-01 00:00:00 毫秒数：" + Instant.now().toEpochMilli());
        
        //LocalTime、LocalDateTime、Intant 的时间差处理 Duration
        System.out.println("时间差：" + Duration.between(LocalTime.parse("01:02:03"), LocalTime.parse("02:03:04")));
        System.out.println("时间差：" + Duration.between(LocalDateTime.parse("2020-09-21T01:02:03"), LocalDateTime.parse("2020-09-22T02:03:04")));
        System.out.println("时间差：" + Duration.between(Instant.ofEpochMilli(1600623455080L), Instant.now()));
        
        //比较
        System.out.println("2020-09-21 在 2020-09-18 前？" + LocalDate.parse("2020-09-21").isBefore(LocalDate.parse("2020-09-18")));
        System.out.println("01:02:03 在 02:03:04 后？" + LocalTime.parse("01:02:03").isAfter(LocalTime.parse("02:03:04")));
        
        //年月日差处理 Period
        System.out.println("日期相差：" + Period.between(LocalDate.of(2020, 9, 8), LocalDate.of(2020, 9, 21)));
        
        //修改，返回日期、时间对象的副本
        System.out.println("修改日期返回副本：" + LocalDate.now().withYear(2019).withMonth(9).withDayOfMonth(9));
        LocalDate date4Cal = LocalDate.now();
        System.out.println("加一周：" + date4Cal.plusWeeks(1));
        System.out.println("减两个月：" + date4Cal.minusMonths(2));
        System.out.println("减三年：" + date4Cal.minusYears(3));
        
        //TemporalAdjuster，Temporal 对象转换，实现自定义
        
        //格式化
        System.out.println("格式化当前日期：" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("格式化当前日期：" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        System.out.println("格式化当前日期时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd  HH:mm:ss")));
        
        //解析
        System.out.println("日期解析：" + LocalDate.parse("2020-09-20"));
        System.out.println("日期解析：" + LocalDate.parse("2020/09/20", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        System.out.println("日期时间解析：" + LocalDateTime.parse("2020/09/20 01:01:03", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        
        //DateTimeFormatterBuilder 更复杂的 DateTimeFormatter 构建
        
        //上海时区
        ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");
        System.out.println("上海时区：" + LocalDate.now().atStartOfDay(shanghaiZone));
        System.out.println("上海时区：" + LocalDateTime.now().atZone(shanghaiZone));
        System.out.println("上海时区：" + Instant.now().atZone(shanghaiZone));

        //子午线时间差
        ZoneOffset offset = ZoneOffset.of("-01:00");
        System.out.println("设置时间差：" + OffsetDateTime.of(LocalDateTime.now(), offset));
        
        //日历系统，ThaiBuddhistDate、MinguoDate、JapaneseDate、HijrahDate，实现 ChronoLocalDate 接口
    }

    /**
     * 测试旧 java.util.Date 类
     */
    @SuppressWarnings("deprecation")
	private static void testOldDate() {
        Date date = new Date();
        System.out.println(date);

        date = new Date(120, 0, 1);
        System.out.println(date);
    }
}
