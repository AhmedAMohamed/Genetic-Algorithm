public class GeneticAlgorithm {

	public static void algo() {
		Population pop = new Population(5);

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

		int averege = pop.evaluate();

		System.out.println();
		
		Population newOne = pop.select();

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

	}

	public static void main(String[] args) {
		algo();
	}
}
