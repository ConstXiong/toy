package constxiong;

public class User {

	@Override
	protected void finalize() throws Throwable {
		System.out.println("我要被 gc 回收了");
		super.finalize();
	}

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
				"}";
	}
}
