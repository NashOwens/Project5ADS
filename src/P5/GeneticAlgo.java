package P5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



public class GeneticAlgo {

    static ArrayList<Double> dataset;
    static double finalFitness = 0;


    static class Individual{

        public ArrayList<Integer> chromosome;
        double fitness;

        public Individual(int n) {


            ArrayList<Integer> chromosome = new ArrayList<>();
            Random r = new Random();

            while(chromosome.size()<n) {
                chromosome.add(r.nextInt(2));
            }

            this.chromosome = chromosome;
            setFitness();
        }

        public void setFitness () {
            double res = 0, l = 0, r = 0;
            int size = dataset.size();
            for (int i=0; i< size-1;i++){
                if(chromosome.get(i)==0){
                    l += dataset.get(i);
                }else{
                    r += dataset.get(i);
                }
                //res += Math.abs(l-r);
            }
            res+= Math.abs(l-r); //x[n][0] - return to the origin

            this.fitness = res;
        }

        public Individual copyGenes(Individual ind) {

            Individual res = new Individual(ind.chromosome.size());

            for (int i=0; i<ind.chromosome.size(); i++) {
                res.chromosome.set(i, ind.chromosome.get(i));
            }

            return res;
        }

        public void printChromosome () {
            System.out.print("\n"+chromosome+" "+fitness+"\n");
        }

    }

    class Population{

        public ArrayList<Individual> population = new ArrayList<>();

        public Population (int popSize, int chromosomeSize) {

            for (int i=0; i<popSize; i++) {
                Individual ind = new Individual(chromosomeSize);
                this.population.add(ind);
            }
        }

        public void printPop() {

            for (Individual individual : population) {
                System.out.print(individual.chromosome + "\t");
                System.out.println(individual.fitness);
            }
        }

        public Individual crossOver (Individual p1, double rate) {

            int point = (int)(p1.chromosome.size()*rate);

            Individual res = new Individual(p1.chromosome.size());
            Random r = new Random();

            for (int i=0; i<point; i++) {
                res.chromosome.set(i, p1.chromosome.get(i)); //copy some genes from the parent
            }

            return res;
        }

        public Individual mutate (Individual p1, double rate) {
            Individual res = p1.copyGenes(p1); //we copy the parent's genes first

            //now we mutate the genes, using small change (swap genes by random)
            Random r = new Random();
            for(int n = 0; n != Math.round(p1.chromosome.size()*rate); n++) {
                int i = r.nextInt(p1.chromosome.size());
                int j = r.nextInt(p1.chromosome.size());

                //to avoid getting the same gene
                while (i == j) {
                    j = r.nextInt(p1.chromosome.size());
                }
                res.chromosome.set(i, p1.chromosome.get(j));
                res.chromosome.set(j, p1.chromosome.get(i));
            }
            return res;
        }
    }

    public static void runGA() throws IOException {

        //create a population object and parameters
        GeneticAlgo ga = new GeneticAlgo();
        int numGeneration = 30;
        int popSize = 10;
        double crossOverRate = 0.1;
        double mutationRate = 0.1;

        //prepare dataset
        String file = "C:\\Gen\\data.csv";
        dataset = Data.readFile(file);
        int chromosomeSize = dataset.size();

        //initialise the population
        Population pop = ga.new Population(popSize, chromosomeSize); //create 10 candidates, each candidates has 5 genes (5 nodes), pass dataset to calculate fitness

        //We sort the candidates by fitness in ascending order, the least the better in this example (TSP)
        pop.population.sort(new CompareFitness()); //sorting the population by fitness (asc)
        System.out.println("====Before Search====");
        pop.printPop();

        for (int gen=0; gen<numGeneration; gen++) {

            System.out.println("Generation : "+gen);

            //get the parents - top 2 from the list
            Individual p1 = pop.population.get(0);
            Individual p2 = pop.population.get(1);

            //get 2 new children
            Individual ch1 = pop.crossOver(p1, crossOverRate);
            Individual ch2 = pop.crossOver(p2, crossOverRate);

            System.out.println("\nChild 1 : ");
            ch1.printChromosome();
            System.out.println("\nChild 2 : ");
            ch2.printChromosome();

            //get a mutate child
            Individual ch3 = pop.mutate(p1, mutationRate);
            System.out.println("\nChild 3 : ");
            ch3.printChromosome();

            //add these new children to the population
            pop.population.add(ch1);
            pop.population.add(ch2);
            pop.population.add(ch3);

            //sort them
            pop.population.sort(new CompareFitness()); //sorting the population by fitness

            //remove the weak candidate
            pop.population.remove(popSize-1);
            pop.population.remove(popSize-1);
            pop.population.remove(popSize-1);

            //pop.printPop();

            String fileName = "C:\\Gen\\"+gen+".csv";
            String average = "C:\\Gen\\avg"+gen+".csv";
            Data.writeAverage(average, pop);
            Data.writeResult(fileName, pop);

        }
        System.out.println("====Result====");
        pop.printPop();
        finalFitness = pop.population.get(0).fitness;
        System.out.println("\nFinal fitness: "+finalFitness);
    }

    public static void main(String[] args) throws IOException {
        runGA();
    }

}