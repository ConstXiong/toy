package constxiong;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

/**
 * 测试泛型 api
 * 
 * @author ConstXiong
 */
public class Test {
	
	public static void main(String[] args) throws Exception {
		typeRemove();
		printType();
		getCollectionGenericType();
		getReturnType();
	}

	/**
	 * 类型擦除
	 */
	private static void typeRemove() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		List temp = list;
		temp.add("ConstXiong");
		System.out.println(list);
	}
	
	/**
	 * 打印类型及泛型信息
	 */
	private static void printType() {
		System.out.println(int.class);
		System.out.println(double[].class);
		System.out.println(Object[].class);
		System.out.println(String.class);
		ParameterizedType genericSuperclass = (ParameterizedType)ArrayList.class.getGenericSuperclass();
		System.out.println(genericSuperclass);
		System.out.println(genericSuperclass.getRawType());
		//泛型信息
		Stream.of(genericSuperclass.getActualTypeArguments()).forEach(type -> {
			System.out.println(type.getClass());
			System.out.println(type.getTypeName());
		});
	}

	/**
	 * 获取集合类型的泛型信息
	 */
	private static void getCollectionGenericType() {
		List<String> list = new ArrayList<String>();
		StringList stringList = new StringList();
		//[2.0, 4.3]
//		GenericCollectionTypeResolver.getCollectionType(list.getClass());
		
		ResolvableType resolvableType = ResolvableType.forClass(list.getClass());
		System.out.println(resolvableType);
		System.out.println(resolvableType.getSuperType());
		//具体化的泛型才能获取到 java.lang.String 的泛型信息
		System.out.println(ResolvableType.forClass(stringList.getClass()).getSuperType());
		System.out.println(ResolvableType.forClass(stringList.getClass()).getSuperType().getGeneric(0));
		
		Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
	}
	
	static class StringList extends ArrayList<String> {
	}
	
	/**
	 * 测试方法返回类型的泛型
	 */
	private static void getReturnType() throws Exception {
		Class clazz = Test.class;
		Method method = clazz.getMethod("getLisit", null);
		System.out.println(method);
		Class<?> returnType = GenericTypeResolver.resolveReturnType(method, clazz);
		System.out.println(returnType);
		Class<?> returnTypeArg = GenericTypeResolver.resolveReturnTypeArgument(method, clazz);
		System.out.println(returnTypeArg);//未获取到返回类型的泛型信息
	}
	
	public static List<String> getLisit() {
		return null;
	}
	
	public static StringList getStringList() {
        return null;
    }
	
	public static String getString() {
        return null;
    }
	
}
