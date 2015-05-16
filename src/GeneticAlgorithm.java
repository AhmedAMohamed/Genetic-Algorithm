import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

import java.util.Random;

public class GeneticAlgorithm {

	public static final double mutationPro = 0.7;
	public static final double crossOverPro = 0.3;


	public static ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
	
	public static void algo(int generatians) {
		Population pop = new Population(5, true);
		int q = 0;
		int q1 = generatians;
		while (q < q1) {			
			int averege = pop.evaluate();
			ArrayList<Integer> values = new ArrayList<>();
			values.add(Individual.toInteger(pop.getFittest().s1)[0]);
			values.add(Individual.toInteger(pop.getFittest().s1)[1]);
			values.add(pop.getFittest().fittness);
			results.add(values);
			
			Population newOne = pop.select();
			newOne = crossOver(newOne);
			pop = mutation(newOne);
			q++;
		}

	}

	public static Population crossOver(Population pop) {
		Population nPop = new Population(pop.size, false);
		for (int i = 0; i < pop.size - 1; i++) {
			Random rand = new Random();
			int index1 = rand.nextInt(pop.size);
			int index2 = rand.nextInt(pop.size);
			if (rand.nextDouble() > crossOverPro) {
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
		Random rand = new Random();
		for (int i = 0; i < pop.size; i++) {
			if (rand.nextDouble() > mutationPro) {
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
		int i = 30;
		int j = 0;
		while (j < i) {
			algo(10);
			j++;
		}	
		int t = 0;
		int y = 0;
		int[] outPut = new int[10];
		System.out.println("result in each generation in all runs");
		for(int z = 0; z < 300; z++) {
			System.out.println(results.get(z).get(2) + "  " + results.get(z).get(0) + "   "  + results.get(z).get(1));
		}
		for(int k = 0; k < 300; ) {
			outPut[0] += results.get(k++).get(2);
			outPut[1] += results.get(k++).get(2);
			outPut[2] += results.get(k++).get(2);
			outPut[3] += results.get(k++).get(2);
			outPut[4] += results.get(k++).get(2);
			outPut[5] += results.get(k++).get(2);
			outPut[6] += results.get(k++).get(2);
			outPut[7] += results.get(k++).get(2);
			outPut[8] += results.get(k++).get(2);
			outPut[9] += results.get(k++).get(2);
		}
		for(int i1 = 0; i1 < 10; i1++) {
			outPut[i1] /= 30;
		}
		
		final Plotter demo = new Plotter("Chart",outPut);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
	}
}
