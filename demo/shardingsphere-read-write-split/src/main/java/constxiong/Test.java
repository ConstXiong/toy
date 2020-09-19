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

/**
 * 测试 ShardingSphere 读写分离
 * 主库：172.31.32.184
 * 从库：172.31.32.234
 *
 * 观察通过 ShardingSphere 获取的 DataSource 是否会自动写入到主库，从库是否主动同步，从库同步数据的延迟时间
 */
public class Test {

    //主库 DataSource
    private static DataSource dsSlave = DataSourceUtil.createDataSource("172.31.32.234", "root", "constxiong@123", "constxiong");
    //从库 DataSource
    private static DataSource dsMaster = DataSourceUtil.createDataSource("172.31.32.184", "root", "constxiong@123", "constxiong");

    public static void main(String[] args) throws SQLException {
        //启动线程打印主库与从库当前 cuser 数据量与时间，观察从库同步数据延迟
        printMasterAndSlaveData();

        //从 ShardingSphere 获取 DataSource，出入数据，观察插入数据的库是否为主库
        DataSource ds = getMasterSlaveDataSource();
        Connection conn = ds.getConnection();
        Statement stt = conn.createStatement();
        stt.execute("insert into cuser values(2, 'fj')");
    }

    /**
     * 启动线程打印，两个主从库 cuser 表的信息、数据量、当前时间
     * @throws SQLException
     */
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

    /**
     * 设置 ShardingSphere 的主从库
     * @return
     * @throws SQLException
     */
    private static DataSource getMasterSlaveDataSource() throws SQLException {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("ds_master_slave", "ds_master", Arrays.asList("ds_slave"));
        return MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(), masterSlaveRuleConfig, new Properties());
    }

    /**
     * 用 主从库的 DataSource 构造 map
     * @return
     */
    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds_master", dsMaster);
        result.put("ds_slave", dsSlave);
        return result;
    }
}
