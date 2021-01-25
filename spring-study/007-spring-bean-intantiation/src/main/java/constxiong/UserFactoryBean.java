package constxiong;

import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {

	private static int id = 10;
	
	@Override
	public User getObject() throws Exception {
		User user = new User();
		user.setId(++id);
		user.setName("FactoryBean");
		return user;
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}


}
