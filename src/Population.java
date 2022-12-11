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

    int ukuranPopulasi;
    Individual[] arrIndividual;
    int fittest = 0;
    int leastFittest = 0;
    int[] table; 
    //Inisialisasi populasi dari array of Individual
    public void inisialisasiPopulasiBaru(Individual[] arrIndividual, int[] table ){
        this.table = table;
        this.ukuranPopulasi = arrIndividual.length;
        this.arrIndividual = new Individual[arrIndividual.length];
        System.arraycopy(arrIndividual, 0, this.arrIndividual, 0, arrIndividual.length);
    }

    //Inisialisasi Populasi
    public void inisialisasiPopulasi(int size, int geneLength, int[] tabel) {
        this.table = tabel;
        this.ukuranPopulasi = size;
        this.arrIndividual = new Individual[this.ukuranPopulasi];
        for (int i = 0; i < arrIndividual.length; i++) {
            arrIndividual[i] = new Individual(geneLength, tabel);
        }
    }

    //Get the fittest individual
    public Individual getFittest() {
        int maxfittest = Integer.MIN_VALUE;
        int idx_maxfittest = 0;
        for (int i = 0; i < arrIndividual.length; i++) {
            if (maxfittest <= arrIndividual[i].fitness) {
                maxfittest = arrIndividual[i].fitness;
                idx_maxfittest = i;
            }
        }
        fittest = arrIndividual[idx_maxfittest].fitness;
        return arrIndividual[idx_maxfittest];
    }
    
    //Get the least fittest individual
    public Individual getLeastFittest() {
        int minFittest = Integer.MAX_VALUE;
        int idx_minFittest = 0;
        for (int i = 0; i < arrIndividual.length; i++) {
            if (minFittest >= arrIndividual[i].fitness) {
                minFittest = arrIndividual[i].fitness;
                idx_minFittest = i;
            }
        }
        leastFittest = arrIndividual[idx_minFittest].fitness;
        return arrIndividual[idx_minFittest];
    }
    
    public Individual[] getarrParentPopulation(){
        Individual[] arrParent = new Individual[this.ukuranPopulasi];
        for(int i =0; i < this.ukuranPopulasi; i++){
            arrParent[i] = new Individual(this.rouletteWheelSelecetion());
        }
        return arrParent;
    }
    
    public Individual rouletteWheelSelecetion(){
        int totalSum = 0;
        for(int i =0; i < this.ukuranPopulasi; i++){
            totalSum += this.arrIndividual[i].fitness;
        }
        Random rand = new Random();
        int random = rand.nextInt(totalSum-1);
        random+=1;
        int sum = 0;
        for(int i =0; i < this.ukuranPopulasi; i++){
            sum += this.arrIndividual[i].fitness;
            if(sum >= random){
                return this.arrIndividual[i];
            }
        }
        return null;
    }

    //Calculate fittest of each individual
    public void hitungFittest() {
        for (Individual individual : arrIndividual) {
            individual.nilaiFitness();
        }
        getFittest();
        getLeastFittest();
    }
}
