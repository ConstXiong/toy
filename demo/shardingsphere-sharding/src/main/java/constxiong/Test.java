package constxiong;

import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 测试 ShardingSphere 分表分库
 */
public class Test {

    //DataSource 0
    private static DataSource ds0 = DataSourceUtil.createDataSource("172.31.32.234", "root", "constxiong@123", "constxiong");
    // DataSource 1
    private static DataSource ds1 = DataSourceUtil.createDataSource("172.31.32.184", "root", "constxiong@123", "constxiong");

    public static void main(String[] args) throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", ds0);
        dataSourceMap.put("ds1", ds1);

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order", "ds$->{0..1}.t_order$->{0..1}");

        // 配置分库 + 分表策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("insert into t_order value(1, 1, 1, '电视机')");
        statement.execute("insert into t_order value(2, 1, 2, '可乐')");
        statement.execute("insert into t_order value(3, 2, 8, '空调')");
        statement.execute("insert into t_order value(4, 2, 9, '手机壳')");

        statement.execute("select * from t_order where user_id = 2");
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            System.out.printf("user_id:%d, order_id:%d, goods_id:%d, goods_name:%s\n",
                    resultSet.getInt("user_id"),
                    resultSet.getInt("order_id"),
                    resultSet.getInt("goods_id"),
                    resultSet.getString("goods_name")
            );
        }
    }
}
