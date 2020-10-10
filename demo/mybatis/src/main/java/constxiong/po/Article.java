package constxiong.po;

public class Article {

    private int userId;

    private String title;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                '}';
    }
}
