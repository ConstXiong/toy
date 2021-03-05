package constxiong;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;

/**
 * 测试 spring resource pattern
 * 
 * @author ConstXiong
 */
public class Test {
	
	static String filePath = Test.class.getClassLoader().getResource("a.txt").getPath();
	
	public static void main(String[] args) throws IOException {
		String dir = System.getProperty("user.dir");
		String end = ".txt";
//		String end = ".java";
		String pattern = "file:///" + dir + "/src/main/java/constxiong/*" + end;
		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
		patternResolver.setPathMatcher(new FilePathMatcher(end));
		Resource[] resources = patternResolver.getResources(pattern);
		Stream.of(resources).forEach(System.out::println);
	}

	private static class FilePathMatcher implements PathMatcher {

		private String end;
		
		FilePathMatcher(String end) {
			this.end = end;
		}
		
		@Override
		public boolean isPattern(String path) {
			return path.endsWith(end);
		}

		@Override
		public boolean match(String pattern, String path) {
			return path.endsWith(end);
		}

		@Override
		public boolean matchStart(String pattern, String path) {
			return false;
		}

		@Override
		public String extractPathWithinPattern(String pattern, String path) {
			return null;
		}

		@Override
		public Map<String, String> extractUriTemplateVariables(String pattern,
				String path) {
			return null;
		}

		@Override
		public Comparator<String> getPatternComparator(String path) {
			return null;
		}

		@Override
		public String combine(String pattern1, String pattern2) {
			return null;
		}
		
	}

}
