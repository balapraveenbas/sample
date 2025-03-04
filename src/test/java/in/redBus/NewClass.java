package in.redBus;

public class NewClass {
	public static void main(String[] args) {
		BrokenLink br = BrokenLink.justInstance();
		BrokenLink br1 = BrokenLink.justInstance();
		BrokenLink br2 = BrokenLink.justInstance();
		System.out.println(System.identityHashCode(br));
		System.out.println(System.identityHashCode(br1));
		System.out.println(System.identityHashCode(br2));
	}
}
