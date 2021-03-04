package constxiong;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		setProperValue("id", element, builder);
		setProperValue("name", element, builder);
	}

	private void setProperValue(String attrName, Element element, BeanDefinitionBuilder builder) {
		String value = element.getAttribute(attrName);
		if (!StringUtils.isEmpty(value)) {
			builder.addPropertyValue(attrName, value);
		}
	}

}
