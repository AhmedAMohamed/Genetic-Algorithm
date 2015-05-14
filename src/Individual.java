import java.util.Random;

public class Individual {

	public boolean[] s1;
	public double fittness;
	public double probability;

	public Individual() {
		s1 = new boolean[10];
	}
	
	public Individual(Individual d) {
		s1 = d.s1;
		fittness = d.fittness;
		probability = d.probability;
	}
	
	public Individual(int s1, int s2) {
		this.s1 = toBinaryArray(s1,s2);
	}

	public static int[] toInteger(boolean[] s) {
		int[] values = new int[2];
		for (int i = 0; i < 5; i++) {
			int x = s[i] ? 1 : 0;
			values[0] += x * Math.pow(2, 5 - i-1);
		}
		System.out.println(values[0]);
		for (int i = 5; i < 10; i++) {
			int x = s[i] ? 1 : 0;
			values[1] += x * Math.pow(2, 10 - i-1);
		}
		return values;
	}

	public static boolean[] toBinaryArray(int s1,int s2) {
		boolean[] w = new boolean[10];
		String f = String.format("%5s", Integer.toBinaryString(s1)).replace(' ',
				'0');
		String g = String.format("%5s", Integer.toBinaryString(s2)).replace(' ', '0');
		f = f+g;
		for (int i = 0; i < w.length; i++) {
			w[i] = f.charAt(i) == '1' ? true : false;
		}
		return w;
	}

	public void calculateFittness() {
		int[] s1 = toInteger(this.s1);
		fittness = Math.pow(10, 6) - (625 - Math.pow(s1[0] - 25, 2))
				* (1600 - Math.pow(s1[1] - 10, 2)) * Math.sin(s1[0] * Math.PI / 10)
				* Math.sin(s1[1] * Math.PI / 10);
		
	}
	
	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public static void main(String[] args) {
		boolean[] e = {true,false,true,true,true,true,true,true,true,true};
		int[] r = toInteger(e);
		System.out.println(r[0]);
		System.out.println(r[1]);
		
	}
}
