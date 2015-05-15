import java.util.Random;

public class GeneticAlgorithm {

	public static void algo() {
		Population pop = new Population(5, true);

		while(true) {
			int averege = pop.evaluate();
			
			if(pop.getFittest().fittness * 0.99 < averege) {
				for (int i = 0; i < 5; i++) {
					for (int k = 0; k < pop.population[i].s1.length; k++) {
						System.out.print(pop.population[i].s1[k] ? '1' : '0');
					}
					System.out.print("    "
							+ Individual.toInteger(pop.population[i].s1)[0] + "   "
							+ Individual.toInteger(pop.population[i].s1)[1] + "   "
							+ pop.population[i].fittness);
					System.out.println();
				}
				System.out.println();
				System.out.println(pop.getFittest().fittness + "  " + Individual.toInteger(pop.getFittest().s1)[0] + "   " + Individual.toInteger(pop.getFittest().s1)[1]);
				break;
			}
			
			Population newOne = pop.select();

			newOne = crossOver(newOne);
			newOne = mutation(newOne);
/*
			for (int i = 0; i < 5; i++) {
				for (int k = 0; k < newOne.population[i].s1.length; k++) {
					System.out.print(newOne.population[i].s1[k] ? '1' : '0');
				}
				System.out.print("    "
						+ Individual.toInteger(newOne.population[i].s1)[0] + "   "
						+ Individual.toInteger(newOne.population[i].s1)[1] + "   "
						+ newOne.population[i].fittness);
				System.out.println();
			}
*/
		}
		
	}

	public static Population crossOver(Population pop) {
		Population nPop = new Population(pop.size, false);
		for (int i = 0; i < pop.size - 1; i++) {
			Random rand = new Random();
			int index1 = rand.nextInt(pop.size);
			int index2 = rand.nextInt(pop.size);
			if (rand.nextBoolean()) {
				Individual[] id = new Individual[2];
				id[0] = new Individual(pop.population[index1]);
				id[1] = new Individual(pop.population[index2]);
				id = Population.crossOver(id);
				nPop.addIndiVitual(i, id[0]);
				nPop.addIndiVitual(i + 1, id[1]);
			} else {
				Individual[] id = new Individual[2];
				id[0] = new Individual(pop.population[index1]);
				id[1] = new Individual(pop.population[index2]);
				nPop.addIndiVitual(i, id[0]);
				nPop.addIndiVitual(i + 1, id[1]);
			}
		}
		return nPop;
	}

	public static Population mutation(Population pop) {

		Population nPop = new Population(pop.size, false);
		System.out.println();
		Random rand = new Random();
		for (int i = 0; i < pop.size; i++) {
			if (rand.nextBoolean()) {
				Individual id = Population.mutate(pop.population[i]);
				nPop.addIndiVitual(i, id);
			} else {
				Individual id = pop.population[i];
				nPop.addIndiVitual(i, id);
			}
		}
		return pop;
	}

	public static void main(String[] args) {
		algo();
	}
}