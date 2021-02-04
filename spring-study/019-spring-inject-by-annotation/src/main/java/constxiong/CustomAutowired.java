package constxiong;

import java.lang.annotation.*;

/**
 * 自定义注解，用来实现类似 @Autowired 功能
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomAutowired {
}
