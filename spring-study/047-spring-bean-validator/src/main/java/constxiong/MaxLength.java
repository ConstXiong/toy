package constxiong;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 自定义字符串最大长度校验注解
 * 
 * @author ConstXiong
 * @date 2021年3月11日 下午7:49:52
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MaxLengthValidator.class)
public @interface MaxLength {
	
	int value() default 10;

	String message() default "超过最大长度";
	
	Class<?> [] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
