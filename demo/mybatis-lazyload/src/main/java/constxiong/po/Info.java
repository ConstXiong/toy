package constxiong.po;

public class Info {

    private int userId;

    private String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Info{" +
                "userId=" + userId +
                ", name=" + name +
                '}';
    }
}
