import java.util.Random;

public class Population {
	Individual[] population;
	int size;

	double mutationRate; // how many bits to mutate
	boolean crossOver; // single or two point crossover

	public Population(int size) {
		population = new Individual[size];
		this.size = size;
		InetializePopulation();
	}

	private void InetializePopulation() {
		Random rand = new Random();
		int s1 = rand.nextInt(32);
		int s2 = rand.nextInt(32);
		for (int i = 0; i < this.size; i++) {
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

	public Individual mutate(Individual id) {
		Individual t = new Individual(id);
		Random rand = new Random();
		for (int i = 0; i < this.size; i++) {
			if (rand.nextBoolean()) {
				t.s1[i] = !t.s1[i];
			}
		}
		for (int i = 0; i < this.size; i++) {
			if (rand.nextBoolean()) {
				t.s2[i] = !t.s2[i];
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

	public Population select() { // the new generation is consist of the best
									// fit add roulette wheel selection
		Population newOne = new Population(this.size);
		double total = 0;
		double sumOfPro = 0;
		for (Individual i : population) {
			total += i.fittness;
		}
		for (Individual i : population) {
			i.probability = sumOfPro + (i.fittness / total);
			sumOfPro += i.probability;
		}
		newOne.addIndiVitual(0, this.getFittest());
		for (int i = 1; i < newOne.size; i++) {
			double probable = Math.random();
			int j = 0;
			for (j = 0; j < this.size; j++) {
				if (probable > this.population[j].probability
						&& probable < this.population[j].probability) {
					break;
				}
			}
			newOne.addIndiVitual(i, this.population[j]);
		}
		return newOne;
	}

	public void addIndiVitual(int index, Individual chromosom) {
		this.population[index] = chromosom;
	}
}
