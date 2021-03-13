package constxiong;

import java.util.Properties;

public class User {
	private Properties property;
	private String propertyText;
	public Properties getProperty() {
		return property;
	}
	public void setProperty(Properties property) {
		this.property = property;
	}
	public String getPropertyText() {
		return propertyText;
	}
	public void setPropertyText(String propertyText) {
		this.propertyText = propertyText;
	}
	@Override
	public String toString() {
		return "User [property=" + property + ", propertyText=" + propertyText + "]";
	}
	
}