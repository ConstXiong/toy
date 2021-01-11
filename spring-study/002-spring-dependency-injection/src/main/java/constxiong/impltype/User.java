package constxiong.impltype;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class User implements ApplicationContextAware {
	
    //构造方法注入
    private Integer id;
    public User(Integer id) {
        this.id = id;
    }
    // set 方法注入
    private String name;
    public void setName(String name) {
        this.name = name;
    }

    //实现接口 ApplicationContextAware 及其回调方法注入 applicationContextAware
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // xml 开启注解能力
    // 使用 @Autowired 给属性字段注入
    @Autowired
    private String favorites;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", applicationContext=" + applicationContext +
                ", friend=" + favorites +
                '}';
    }
}
