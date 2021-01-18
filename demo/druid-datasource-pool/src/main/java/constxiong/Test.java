package constxiong;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

    public static void main(String[] args) throws Exception {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/constxiong?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        ds.setUsername("root");
        ds.setPassword("constxiong@123");
        try (Connection connection = ds.getConnection(); Statement statement = connection.createStatement()){
             statement.execute("select * from user");
             try(ResultSet resultSet = statement.getResultSet()){
                 while (resultSet.next()) {
                     Integer id = resultSet.getInt(1);
                     String name = resultSet.getString(2);
                     System.out.println(id + "-" + name);
                 }
             }
        }
        ds.close();
    }
}