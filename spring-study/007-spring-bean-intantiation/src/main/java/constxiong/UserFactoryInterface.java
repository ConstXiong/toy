package constxiong;

public interface UserFactoryInterface {
	
	default User getUser() {
		User user = new User();
		user.setName("bean factory interface");
		return user;
	}

}
