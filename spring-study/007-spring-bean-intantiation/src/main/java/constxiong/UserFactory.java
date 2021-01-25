package constxiong;

public class UserFactory {
	
	private static int id = 1;
	
	public User getUser() {
		User user = new User();
		user.setId(++id);
		user.setName("bean factory");
		return user;
	}

}
