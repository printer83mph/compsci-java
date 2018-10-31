package linkedLists;

public class LinkTesto {
	public static void main(String[] args) {
		IntLinkeListo l = new IntLinkeListo();
		l.push(1);
		l.push(2);
		l.push(3);
		System.out.println(l);
		int i = l.shift();
		l.push(i);
		System.out.println(l);
	}
}
