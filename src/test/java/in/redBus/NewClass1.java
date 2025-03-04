package in.redBus;

public class NewClass1 {
public static void main(String[] args) {
	BrokenLink br = new BrokenLink();
	System.out.println(System.identityHashCode(br));
	//392292416
}
}
