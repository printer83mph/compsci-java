package euler;

public class Euler {
	public static long e1(int limit) {
		long out = 0;
		for(int i = 0; i < limit; i++) {
			if (i % 3 == 0 || i % 5 == 0) {
				out += i;
			}
		}
		return out;
	}
	
	public static long e2(int limit) {
		int i = 1;
		int j = 1;
		long out = 0;
		while(j < limit) {
			int oldj = j;
			if (j % 2 == 0) {
				out += j;
			}
			j += i;
			i = oldj;
		}
		return out;
	}

}