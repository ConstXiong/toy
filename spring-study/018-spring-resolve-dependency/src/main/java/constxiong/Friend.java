package constxiong;

import org.springframework.stereotype.Component;

@Component
public class Friend {

    private String name = "friend";

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                '}';
    }
}
