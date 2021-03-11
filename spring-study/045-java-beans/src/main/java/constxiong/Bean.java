package constxiong;

/**
 * 测试 Bean，此类并不符合 JavaBeans 规范
 * 
 * @author ConstXiong
 * @date 2021年3月11日 下午8:58:24
 */
public class Bean {
	
	private Bean(){
	}
	
	public static Bean getInstance() {
		return new Bean();
	}
	
	private int id;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
