package constxiong;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

public class User implements
        BeanFactoryAware,                //获取 IoC 容器 BeanFactory 的能力
        ApplicationContextAware,         //获取 Spring 应用上下文 ApplicationContext 对象的能力
        EnvironmentAware,                //获取 Environment 对象的能力
        ResourceLoaderAware,             //获取资源加载器对象 ResourceLoader 的能力
        BeanClassLoaderAware,            //获取加载当前 Bean Class 的 ClassLoader 的能力
        BeanNameAware,                   //获取当前 Bean 名称的能力
        MessageSourceAware,              //获取用于国际化 MessageSource 对象的能力
        ApplicationEventPublisherAware,  //获取事件发布对象 ApplicationEventPublisher 的能力
        EmbeddedValueResolverAware       //获取占位符处理对象 StringValueResolver 的能力
{

    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private Environment environment;
    private ResourceLoader resourceLoader;
    private ClassLoader beanClassLoader;
    private String beanName;
    private MessageSource messageSource;
    private ApplicationEventPublisher applicationEventPublisher;
    private StringValueResolver embeddedValueResolver;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "beanFactory=" + beanFactory + '\n' +
                "applicationContext=" + applicationContext + '\n' +
                "environment=" + environment + '\n' +
                "resourceLoader=" + resourceLoader + '\n' +
                "beanClassLoader=" + beanClassLoader + '\n' +
                "beanName='" + beanName + '\'' + '\n' +
                "messageSource=" + messageSource + '\n' +
                "applicationEventPublisher=" + applicationEventPublisher + '\n' +
                "embeddedValueResolver=" + embeddedValueResolver + '\n' +
                "id=" + id +
                '}';
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.embeddedValueResolver = stringValueResolver;
    }
}
