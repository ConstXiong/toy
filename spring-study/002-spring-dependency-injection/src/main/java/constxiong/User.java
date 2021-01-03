package constxiong;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

public class User {

    private Integer id = 1;

    private String name = "Constxiong";
    
    private Favorite favorite;
    
    private List<Friend> friends;
    
    private BeanFactory beanFactory;
    
    private ObjectFactory<ApplicationContext> objectFacotory;

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

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}
	
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public ObjectFactory<ApplicationContext> getObjectFacotory() {
		return objectFacotory;
	}

	public void setObjectFacotory(ObjectFactory<ApplicationContext> objectFacotory) {
		this.objectFacotory = objectFacotory;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", favorite=" + favorite + ", friends=" + friends + "]";
	}
	
}
