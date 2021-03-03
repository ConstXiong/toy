package constxiong;

public class User extends SuperUser {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + getId() + ",name=" + name + "]";
	}

}
