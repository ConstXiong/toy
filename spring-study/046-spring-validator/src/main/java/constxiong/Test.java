package constxiong;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * 测试 Spring Validator 接口
 * 使用模式： Validator#validate(bean, Errors)
 * 
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) {
		Bean bean = new Bean();
		Errors errors = new BeanPropertyBindingResult(bean, "user");
		MessageSource sms = getMessageSource();
		BeanValidator validator = new BeanValidator();
		if (validator.supports(bean.getClass())) {
			validator.validate(bean, errors);
			errors.getAllErrors().forEach(error -> {
				System.out.println(error);
				System.out.println(sms.getMessage(error.getCode(), error.getArguments(), null));
			});
			
			System.out.println("=========== validate again ===========");
			bean.setId(1);
			bean.setName("ConstXiong * 2");
			errors = new BeanPropertyBindingResult(bean, "user2");
			validator.validate(bean, errors);
			errors.getAllErrors().forEach(error -> {
				System.out.println(error);
				System.out.println(sms.getMessage(error.getCode(), error.getArguments(), null));
			});
		} else {
			System.out.println("BeanValidator 不支持该类型");
		}
	}
	
	/**
	 * bean 的校验
	 */
	private static class BeanValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return Bean.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.not.null");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.not.blank");
			Bean bean = (Bean)target;
			String name = bean.getName();
			if (name != null && name.length() > 10) {
				errors.reject("name.too.long");
			}
		}
	}
	
	/**
	 * 获取文案信息
	 */
	private static MessageSource getMessageSource() {
		StaticMessageSource messageSource = new StaticMessageSource();
		Locale locale = Locale.getDefault();
		messageSource.addMessage("id.not.null", locale, "id不能为空");
		messageSource.addMessage("name.not.blank", locale, "name不能为空");
		messageSource.addMessage("name.too.long", locale, "name过长");
		return messageSource;
	}
	
}
