package constxiong.datatype;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class User {
	
	private double id;
	
	private String name;
	
	private User wife;
	
	private User[] friends;
	
	private List<User> schoolmates;
	
	private Set<String> favorites;
	
	private Map<String, String> emails;
	
	public void setId(double id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWife(User wife) {
		this.wife = wife;
	}

	public void setFriends(User[] friends) {
		this.friends = friends;
	}

	public void setSchoolmates(List<User> schoolmates) {
		this.schoolmates = schoolmates;
	}

	public void setFavorites(Set<String> favorites) {
		this.favorites = favorites;
	}

	public void setEmails(Map<String, String> emails) {
		this.emails = emails;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", wife=" + wife + ", friends=" + Arrays.toString(friends)
				+ ", schoolmates=" + schoolmates + ", favorites=" + favorites + ", emails=" + emails + "]";
	}

}
