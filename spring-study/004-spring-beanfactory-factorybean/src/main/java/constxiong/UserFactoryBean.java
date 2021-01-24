package constxiong;

import org.springframework.beans.factory.FactoryBean;

/**
 * UserFactoryBean 实现 FactoryBean 接口，用于构造 User 的bean
 * @author ConstXiong
 */
public class UserFactoryBean implements FactoryBean<User> {

	@Override
	public User getObject() throws Exception {
		User user = new User();
		user.setId(2);
		user.setName("customUser");
		return user;
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
}
