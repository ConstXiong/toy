package constxiong;

import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.*;

public class Test {

    private static DataSource dsMaster = DataSourceUtil.createDataSource("172.31.32.184", "root", "constxiong@123", "constxiong");
    private static DataSource dsSlave = DataSourceUtil.createDataSource("172.31.32.234", "root", "constxiong@123", "constxiong");

    public static void main(String[] args) throws SQLException {
        printMasterAndSlaveData();
        DataSource ds = getMasterSlaveDataSource();
        Connection conn = ds.getConnection();
        Statement stt = conn.createStatement();
        stt.execute("insert into cuser values(2, 'fj')");
    }

    private static void printMasterAndSlaveData() throws SQLException {
        Connection masterConn = dsMaster.getConnection();
        Connection slaveConn = dsSlave.getConnection();
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("------master------" + LocalTime.now());
                    print(masterConn);
                    System.out.println("------slave------" + LocalTime.now());
                    print(slaveConn);
                } catch (SQLException e) {
                }
            }
        }).start();
    }

    private static void print(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("select * from cuser");
        ResultSet rs = statement.getResultSet();
        int count = 0;
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println(id + "-" + name);
            count++;
        }
        System.out.println("total: " + count);
    }

    private static DataSource getMasterSlaveDataSource() throws SQLException {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("ds_master_slave", "ds_master", Arrays.asList("ds_slave"));
        return MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(), masterSlaveRuleConfig, new Properties());
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds_master", dsMaster);
        result.put("ds_slave", dsSlave);
        return result;
    }
}
