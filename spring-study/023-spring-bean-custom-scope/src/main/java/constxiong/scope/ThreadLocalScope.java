package constxiong.scope;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 线程级别 scope
 * 
 * @author ConstXiong
 */
public class ThreadLocalScope implements Scope {
	
	public static final String SCOPE_NAME = "thread-local";
	
	private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<Map<String, Object>>() {
		@Override
		protected Map<String, Object> initialValue() {
	        return new HashMap<>();
	    }
	}; 

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Map<String, Object> map = THREAD_LOCAL.get();
		Object object = map.get(name);
		if (object == null) {
			object = objectFactory.getObject();
			map.put(name, object);
		}
		return object;
	}

	@Override
	public Object remove(String name) {
		return THREAD_LOCAL.get().remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
	}

	@Override
	public Object resolveContextualObject(String key) {
		return THREAD_LOCAL.get().remove(key);
	}

	@Override
	public String getConversationId() {
		return String.valueOf(Thread.currentThread().getId());
	}

}
