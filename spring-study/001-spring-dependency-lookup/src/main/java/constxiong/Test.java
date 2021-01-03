package constxiong;

import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 依赖查找
 */
@SuppressWarnings("deprecation")
public class Test {

    public static void main(String[] args) {
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("META-INF/spring-dependency-lookup.xml"));
        findByName(factory);
        findPrimaryByType(factory);
        findAllByType(factory);
        findByNameAndType(factory);
        findByAnnotation(factory);
        findInLazy(factory);
    }

    /**
     * 根据名称查找
     */
    public static void findByName(BeanFactory factory) {
        User user = (User)factory.getBean("user1");
        System.out.println("根据名称查找: " + user);
    }

    /**
     * 根据类型查找 primary
     */
    private static void findPrimaryByType(BeanFactory factory) {
        //根据类型查找如何存在多个同一类型的bean，需要制定 primary 属性为 true
        User user = factory.getBean(User.class);
        System.out.println("根据类型查找 priary: " + user);
    }

    /**
     * 根据类型查找所有
     */
    private static void findAllByType(BeanFactory factory) {
        //ListableBeanFactory，具有查询所有类型的能力
        Map<String, User> beans = ((ListableBeanFactory) factory).getBeansOfType(User.class);
        System.out.println("根据类型查找所有: " + beans);
    }

    /**
     * 根据 名称 + 类型 查找
     */
    private static void findByNameAndType(BeanFactory factory) {
        User user = factory.getBean("user1", User.class);
        System.out.println("根据 名称 + 类型 查找: " + user);
    }

    /**
     * 根据注解查找
     */
    private static void findByAnnotation(BeanFactory factory) {
        //ListableBeanFactory，具有根据注解查找 bean 的能力
        Map<String, Object> beans = ((ListableBeanFactory) factory).getBeansWithAnnotation(ConstXiong.class);
        System.out.println("根据注解查找: " + beans);
    }

    /**
     * 延迟查找
     */
    private static void findInLazy(BeanFactory factory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>)factory.getBean("lazyUser");
        User user = objectFactory.getObject();
        System.out.println("延迟查找: " + user);
    }

}
