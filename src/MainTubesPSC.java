/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alvin
 */

import java.util.*;
public class MainTubesPSC {
    Population population = new Population();   //Men-generate populasi 
    Population fittest;                         
    int populationSize;
    long generationCount = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        MainTubesPSC demo = new MainTubesPSC();
        System.out.print("Ukuran Populasi Awal: ");
        int populationSize = sc.nextInt();
        System.out.print("Ukuran tabel: ");
        int panjangTabel = sc.nextInt();
        int[] tabel = new int[panjangTabel*panjangTabel];
        System.out.println("Masukan tabel: ");
        for(int i =0; i < panjangTabel*panjangTabel; i++){
            tabel[i] = sc.nextInt();
        }
        //Initialize population
        demo.population.initializePopulation(populationSize, panjangTabel*panjangTabel, tabel);
        //While population gets an individual with maximum fitness
        do{
            //Calculate new fitness value
            demo.population.calculateFitness();
            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest +" Least Fittest: " +demo.population.leastFittest);
            demo.generationCount++;
            if(demo.population.fittest < panjangTabel*panjangTabel*panjangTabel*panjangTabel*9){
                //Do selection
                demo.selection();
                //Do crossover
                demo.crossover();
                //Do mutation under a random probability
                for(int i = 0; i < populationSize; i++){
                    if (rand.nextInt(10000) < 1) {
                        demo.mutation(i);
                    }
                }
                //Add fittest offspring to population
                demo.newPopulation();
            }
        }while (demo.population.fittest < panjangTabel*panjangTabel*panjangTabel*panjangTabel*9);
        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: "+demo.population.getFittest().fitness);
        System.out.println("Genes: ");
        for (int i = 0; i < panjangTabel; i++) {
            for(int j =0; j < panjangTabel; j++){
                System.out.print(demo.population.getFittest().genes[i*panjangTabel + j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    //Selection
    public void selection() {
        //Select the most fittest individual
        fittest = new Population();
        fittest.initializeNewPopulation(population.getParentsPopulation(), population.table);
    } 
    //Crossover
    public void crossover() {
        Random rand = new Random();
        //Select a random crossover point
        int crossOverPoint = Math.abs(rand.nextInt(fittest.arrIndividual[0].geneLength));
        //Swap values among parents
        for(int i =0; i < fittest.arrIndividual.length; i+=2){
            for (int j = 0; j < crossOverPoint; j++) {
                int temp = fittest.arrIndividual[i].genes[j];
                fittest.arrIndividual[i].genes[j] = fittest.arrIndividual[i+1].genes[j];
                fittest.arrIndividual[i+1].genes[j]= temp;
            }
        }
    }
    
    //Mutation
    public void mutation(int idx) {
        Random rand = new Random();
        //Select a random mutation point
        int mutationPoint = rand.nextInt(fittest.arrIndividual[0].geneLength);
        //Flip values at the mutation point
        if(fittest.arrIndividual[idx].genes[mutationPoint] == 0){
            fittest.arrIndividual[idx].genes[mutationPoint] = 1;
        }else{
            fittest.arrIndividual[idx].genes[mutationPoint] = 0;
        }
    }
    //Get fittest offspring
    public Individual getFittestOffspring() {
        return population.getFittest();
    }
    
    //Menggantikan populasi yang lama menjadi populasi yang baru
    public void newPopulation() {
        population = new Population();
        population.initializeNewPopulation(fittest.arrIndividual, fittest.table);
    }
}
