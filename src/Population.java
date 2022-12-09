/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alvin
 */
import java.util.Random;
//Population class
class Population {

    int popSize;
    Individual[] individuals;
    int fittest = 0;
    int leastFittest = 0;
    int[] table; 
    //Inisialisasi populasi dari array of Individual
    public void initializeNewPopulation(Individual[] individuals, int[] table ){
        this.table = table;
        this.popSize = individuals.length;
        this.individuals = new Individual[individuals.length];
        System.arraycopy(individuals, 0, this.individuals, 0, individuals.length);
    }

    //Inisialisasi Populasi
    public void initializePopulation(int size, int geneLength, int[] tabel) {
        this.table = tabel;
        this.popSize = size;
        this.individuals = new Individual[this.popSize];
        for (int i = 0; i < individuals.length; i++) {
            individuals[i] = new Individual(geneLength, tabel);
        }
    }

    //Get the fittest individual
    public Individual getFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].fitness) {
                maxFit = individuals[i].fitness;
                maxFitIndex = i;
            }
        }
        fittest = individuals[maxFitIndex].fitness;
        return individuals[maxFitIndex];
    }
    
    //Get the least fittest individual
    public Individual getLeastFittest() {
        int minFit = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFit >= individuals[i].fitness) {
                minFit = individuals[i].fitness;
                minFitIndex = i;
            }
        }
        leastFittest = individuals[minFitIndex].fitness;
        return individuals[minFitIndex];
    }
    
    public Individual[] getParentsPopulation(){
        Individual[] parents = new Individual[this.popSize];
        for(int i =0; i < this.popSize; i++){
            parents[i] = new Individual(this.rouletteWheelSelecetion());
        }
        return parents;
    }
    
    public Individual rouletteWheelSelecetion(){
        int totalSum = 0;
        for(int i =0; i < this.popSize; i++){
            totalSum += this.individuals[i].fitness;
        }
        Random rd = new Random();
        int rand = rd.nextInt(totalSum-1);
        rand++;
        int sum = 0;
        for(int i =0; i < this.popSize; i++){
            sum += this.individuals[i].fitness;
            if(sum >= rand){
                return this.individuals[i];
            }
        }
        return null;
    }

    //Calculate fitness of each individual
    public void calculateFitness() {
        for (Individual individual : individuals) {
            individual.calcFitness();
        }
        getFittest();
        getLeastFittest();
    }
}
