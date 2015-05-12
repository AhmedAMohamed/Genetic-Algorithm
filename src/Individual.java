import java.util.Random;

public class Individual {

	public boolean[] s1;
	public boolean[] s2;
	public double fittness;

	public Individual() {
		s1 = new boolean[5];
		s2 = new boolean[5];
	}
	
	public Individual(int s1, int s2) {
		this.s1 = toBinaryArray(s1);
		this.s2 = toBinaryArray(s2);
	}

	public static int toInteger(boolean[] s) {
		int value = 0;
		for (int i = 0; i < 5; i++) {
			int x = s[i] ? 1 : 0;
			value += x * Math.pow(2, 5 - i);
		}
		return value;
	}

	public static boolean[] toBinaryArray(int s) {
		boolean[] w = new boolean[5];
		String f = String.format("%5s", Integer.toBinaryString(s)).replace(' ',
				'0');
		for (int i = 0; i < 5; i++) {
			w[i] = f.charAt(i) == '1' ? true : false;
		}
		return w;
	}

	public void calculateFittness() {
		int s1 = toInteger(this.s1);
		int s2 = toInteger(this.s2);
		fittness = Math.pow(10, 6) - (625 - Math.pow(s1 - 25, 2))
				* (1600 - Math.pow(s2 - 10, 2)) * Math.sin(s1 * Math.PI / 10)
				* Math.sin(s2 * Math.PI / 10);
		
	}

	public static void main(String[] args) {
		Random rand = new Random();
		while(true){
			int y = rand .nextInt(32);
			System.out.println(y);
		}
	}
}
