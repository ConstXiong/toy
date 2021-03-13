package constxiong;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * String 与 Properties 之间的转换
 * 
 * @author ConstXiong
 */
public class CustomPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

	/**
	 * Properties -> String
	 */
	@Override
	public String getAsText() {
		Properties properties = (Properties) getValue();
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Object, Object> property : properties.entrySet()) {
			sb.append(property.getKey()).append("=")
			.append(property.getValue()).append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	/**
	 * String -> Properties
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Properties properties = new Properties();
		try {
			properties.load(new StringReader(text));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setValue(properties);
	}
}
