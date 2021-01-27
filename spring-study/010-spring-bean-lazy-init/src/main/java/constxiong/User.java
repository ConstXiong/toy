package constxiong;

public class User {

	private Integer id;

	private String name;

	public User(Integer id) {
		this.id = id;
		System.out.println("init id:" + id);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				"}\n" + super.toString();
	}
}
