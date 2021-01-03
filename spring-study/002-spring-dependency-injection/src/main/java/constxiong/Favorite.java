package constxiong;

public class Favorite {
	
	private String name;

	public Favorite(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Favorite [name=" + name + "]";
	}
	
}
