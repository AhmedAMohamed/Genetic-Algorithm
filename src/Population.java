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
		for(int i = 0; i < this.size; i++) {
			population[i] = new Individual(s1,s2);
			population[i].calculateFittness();
		}
	}
	
	public Individual getFittest() {
		Individual best = population[0];
		for(int i = 0; i < this.size; i++) {
			if(population[i].fittness > best.fittness) {
				best = population[i];
			}
		}
		return best;
	}
	public int evaluate() {
		int avarage = 0;
		for(int i = 0; i < this.size; i++) {
			population[i].calculateFittness();
			avarage += population[i].fittness;
		}
		avarage = avarage / this.size;
		return avarage;
	}
	
	public Population select() {
		return null;
	}
	
	public Population crossOver() {
		return null;
	}
	
	public Population mutate() {
		return null;
	}
}
