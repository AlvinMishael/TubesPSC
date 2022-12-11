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

    int populationSize;
    Individual[] arrIndividual;
    int fittest = 0;
    int leastFittest = 0;
    int[] table; 
    //Inisialisasi populasi dari array of Individual
    public void initializeNewPopulation(Individual[] arrIndividual, int[] table ){
        this.table = table;
        this.populationSize = arrIndividual.length;
        this.arrIndividual = new Individual[arrIndividual.length];
        System.arraycopy(arrIndividual, 0, this.arrIndividual, 0, arrIndividual.length);
    }

    //Inisialisasi Populasi
    public void initializePopulation(int size, int geneLength, int[] tabel) {
        this.table = tabel;
        this.populationSize = size;
        this.arrIndividual = new Individual[this.populationSize];
        for (int i = 0; i < arrIndividual.length; i++) {
            arrIndividual[i] = new Individual(geneLength, tabel);
        }
    }

    //Get the fittest individual
    public Individual getFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < arrIndividual.length; i++) {
            if (maxFit <= arrIndividual[i].fitness) {
                maxFit = arrIndividual[i].fitness;
                maxFitIndex = i;
            }
        }
        fittest = arrIndividual[maxFitIndex].fitness;
        return arrIndividual[maxFitIndex];
    }
    
    //Get the least fittest individual
    public Individual getLeastFittest() {
        int minFit = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < arrIndividual.length; i++) {
            if (minFit >= arrIndividual[i].fitness) {
                minFit = arrIndividual[i].fitness;
                minFitIndex = i;
            }
        }
        leastFittest = arrIndividual[minFitIndex].fitness;
        return arrIndividual[minFitIndex];
    }
    
    public Individual[] getParentsPopulation(){
        Individual[] parents = new Individual[this.populationSize];
        for(int i =0; i < this.populationSize; i++){
            parents[i] = new Individual(this.rouletteWheelSelecetion());
        }
        return parents;
    }
    
    public Individual rouletteWheelSelecetion(){
        int totalSum = 0;
        for(int i =0; i < this.populationSize; i++){
            totalSum += this.arrIndividual[i].fitness;
        }
        Random rand = new Random();
        int random = rand.nextInt(totalSum-1);
        random++;
        int sum = 0;
        for(int i =0; i < this.populationSize; i++){
            sum += this.arrIndividual[i].fitness;
            if(sum >= random){
                return this.arrIndividual[i];
            }
        }
        return null;
    }

    //Calculate fitness of each individual
    public void calculateFitness() {
        for (Individual individual : arrIndividual) {
            individual.calcFitness();
        }
        getFittest();
        getLeastFittest();
    }
}
