package constxiong;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

/**
 * Properties 转 String，Converter
 * 
 * @author ConstXiong
 */
public class CustomPropertyConverter implements ConditionalGenericConverter{

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(Properties.class, String.class));
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (source != null) {
			Properties properties = (Properties)source;
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<Object, Object> property : properties.entrySet()) {
				sb.append(property.getKey()).append("=")
				.append(property.getValue()).append(System.getProperty("line.separator"));
			}
			return sb.toString();
		}
		return null;
	}

	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		return Properties.class.equals(sourceType.getObjectType())
				&& String.class.equals(targetType.getObjectType());
	}

}
