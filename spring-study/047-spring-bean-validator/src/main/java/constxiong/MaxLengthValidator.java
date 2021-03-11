package constxiong;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 最大长度校验实现类
 * 
 * @author ConstXiong
 * @date 2021年3月11日 下午7:50:20
 */
public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		System.out.println(context);
		if (value != null && value.length() > 10) {
			return false;
		}
		return true;
	}

}
