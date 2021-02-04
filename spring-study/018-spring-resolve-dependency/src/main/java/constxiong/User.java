package constxiong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

    @Autowired
    private Friend friend;

    @Override
    public String toString() {
        return "User{" +
                "friend=" + friend +
                '}';
    }
}
