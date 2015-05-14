import java.util.Random;

public class Individual {

	public boolean[] s1;
	public boolean[] s2;
	public double fittness;
	public double probability;

	public Individual() {
		s1 = new boolean[5];
		s2 = new boolean[5];
	}
	
	public Individual(Individual d) {
		s1 = d.s1;
		s2 = d.s2;
		fittness = d.fittness;
		probability = d.probability;
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
	
	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public static void main(String[] args) {
		Individual[] t = new Individual[2];
		for(int i = 0; i < t.length; i++){
			t[i] = new Individual();
			Random e = new Random();
			for(int j = 0; j < t[i].s1.length; j++) {
				t[i].s1[j] = e.nextBoolean();
			}
		}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < t[i].s1.length; j++) {
				System.out.print(t[i].s1[j]?'1' : '0');
			}
			System.out.println();
		}
		Individual[] q = Population.crossOver(t);
		System.out.println();
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < q[i].s1.length; j++) {
				System.out.print(q[i].s1[j]?'1' : '0');
			}
			System.out.println();
		}
	}
}
