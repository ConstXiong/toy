package constxiong;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * 使用 @Autowired 标注，相当于继承自 @Autowired
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface AutowiredExt {
}
