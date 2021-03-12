package constxiong;

public class User {
	private Integer id;
	private String name;
	private Favorite favorite;
	public Integer getId() {
		return id;
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
	public Favorite getFavorite() {
		return favorite;
	}
	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", favorite=" + favorite + "]";
	}
}

class Favorite {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return "Favorite [name=" + name + "]";
	}
}