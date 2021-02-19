package constxiong;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("user1")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class User {

}
