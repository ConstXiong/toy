package constxiong.plugin;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * MyBatis 插件
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class ExamplePlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        for (Object arg : invocation.getArgs()) {
            System.out.println("参数：" + arg);
        }
        System.out.println("方法：" + invocation.getMethod());
        System.out.println("目标对象：" + invocation.getTarget());
        Object result = invocation.proceed();

        //只获取第一个数据
        if (result instanceof List){
            System.out.println("原集合数据：" + result);
            System.out.println("只获取第一个对象");
            List list = (List)result;
            return Arrays.asList(list.get(0));
        }
        return result;
    }
}
