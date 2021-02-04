package constxiong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;

@Component
public class User {

    private Friend friend;

    //构造方法注入
    public User(Friend friend) {
        this.friend = friend;
    }

    //非静态字段注入
    @Autowired
    private Friend friend1;

    private Friend friend2;

    //非静态方法注入
    @Autowired
    public void setFriend(Friend f) {
        this.friend2 = f;
    }

    //自定义 @Autowried 扩展注解 @AutowriedExt 注入
    @AutowiredExt
    private Friend friend3;

    //添加 @CustomAutowired 到依赖注入处理注解
    @Bean
    public static AutowiredAnnotationBeanPostProcessor injectCustomAutowired() {
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setAutowiredAnnotationType(CustomAutowired.class);
        return processor;
    }
    //自定义注解 @CustomAutowried 注入
    @CustomAutowired
    private Friend friend4;

    @Resource
    private Friend friend5;

    @Inject
    private Friend friend6;

    @Override
    public String toString() {
        return "User{" +
                "friend=" + friend +
                ", friend1=" + friend1 +
                ", friend2=" + friend2 +
                ", friend3=" + friend3 +
                ", friend4=" + friend4 +
                ", friend5=" + friend5 +
                ", friend6=" + friend6 +
                '}';
    }
}

@Component
class Friend {

    private String name = "friend";

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                '}';
    }
}