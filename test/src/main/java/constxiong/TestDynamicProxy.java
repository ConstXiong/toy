package constxiong;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 测试动态代理
 * @author ConstXiong
 */
public class TestDynamicProxy implements InvocationHandler {

    public static void main(String[] args) {
        TestDynamicProxy proxy = new TestDynamicProxy(new AImpl());
        A a = (A)proxy.getProxy();
        a.say();
    }

    private Object target;

    public TestDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before...");
        Object result = method.invoke(target, args);
        System.out.println("after...");
        return result;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}

interface A {
    void say();
}

class AImpl implements A{

    @Override
    public void say() {
        System.out.println("a");
    }
}
