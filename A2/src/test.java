import java.io.IOException;

public class test {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		TaggerExample tagging = new TaggerExample();
        tagging.tag("If you have several test classes, you can combine them into a test suite.");
	}
}
