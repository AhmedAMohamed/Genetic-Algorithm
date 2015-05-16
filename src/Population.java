import java.util.Random;

public class Population {
	Individual[] population;
	int size;
	
	public static final double mutate = 0.0016;
	
	public Population(int size, boolean state) {
		population = new Individual[size];
		this.size = size;
		if(state)
			InetializePopulation();
	}

	private void InetializePopulation() {
		Random rand = new Random();
		for (int i = 0; i < this.size; i++) {
			int s1 = rand.nextInt(32);
			int s2 = rand.nextInt(32);
			population[i] = new Individual(s1, s2);
			population[i].calculateFittness();
		}
	}

	public Individual getFittest() {
		Individual best = population[0];
		for (int i = 0; i < this.size; i++) {
			if (population[i].fittness > best.fittness) {
				best = population[i];
			}
		}
		return best;
	}

	public int evaluate() {
		int avarage = 0;
		for (int i = 0; i < this.size; i++) {
			population[i].calculateFittness();
			avarage += population[i].fittness;
		}
		avarage = avarage / this.size;
		return avarage;
	}

	public static Individual mutate(Individual id) {
		Individual t = new Individual(id);
		Random rand = new Random();
		for (int i = 0; i < id.s1.length; i++) {
			if (rand.nextDouble() > mutate) {
				t.s1[i] = !id.s1[i];
			}
		}
		return t;
	}

	public static Individual[] crossOver(Individual[] id) {
		Individual[] t = new Individual[2];
		for (int i = 0; i < t.length; i++) {
			t[i] = new Individual(id[i]);
		}
		Random rand = new Random();
		int position = rand.nextInt(id[0].s1.length - 1);
		for (int j = position + 1; j < t[0].s1.length; j++) {
			boolean r = id[0].s1[j];
			t[0].s1[j] = id[1].s1[j];
			t[1].s1[j] = r;
		}
		return t;
	}

	public Population select() {
		Population newOne = new Population(this.size, false);
		double total = 0;
		double sumOfPro = 0;
		newOne.addIndiVitual(0, getFittest());
		newOne.addIndiVitual(1, getFittest());
		newOne.addIndiVitual(2, getFittest());
		
		for (Individual i : population) {
			total += i.fittness;
		}
		for (int i = 0; i < population.length; i++) {
			population[i].probability = sumOfPro + (population[i].fittness / total);
			sumOfPro = population[i].probability;
		}
		int k = 3;
		for (int i = 1; i < newOne.size && k < newOne.size; i++) {
			double probable = Math.random();
			int j = 0;
			for (j = 0; j < this.size; j++) {
				if (probable > this.population[j].probability
						&& probable < this.population[j+1].probability) {
					break;
				}
			}
			if(j == this.size)
				j--;
			newOne.addIndiVitual(k++, this.population[j]);
		}
		return newOne;
	}

	public void addIndiVitual(int index, Individual chromosom) {
		this.population[index] = chromosom;
	}
}
