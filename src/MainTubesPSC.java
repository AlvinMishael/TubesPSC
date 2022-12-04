/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alvin
 */

import java.util.Random;
import java.util.Scanner;
public class MainTubesPSC {
    Population population = new Population();
    Population fittest;
    int popsize;
    int generationCount = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        MainTubesPSC demo = new MainTubesPSC();
        System.out.print("Ukuran Populasi Awal: ");
        int popSize = sc.nextInt();
        System.out.print("Ukuran tabel: ");
        int panjangTabel = sc.nextInt();
        int[] tabel = new int[panjangTabel*panjangTabel];
        System.out.println("Masukan tabel:");
        for(int i =0; i < panjangTabel*panjangTabel; i++){
            tabel[i] = sc.nextInt();
        }
        //Initialize population
        demo.population.initializePopulation(popSize, panjangTabel*panjangTabel, tabel);
        //Calculate fitness of each individual
        demo.population.calculateFitness();
        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        //While population gets an individual with maximum fitness
        while (demo.population.fittest < 100) {
            ++demo.generationCount;
            //Do selection
            demo.selection();
            //Do crossover
            demo.crossover();
            //Do mutation under a random probability
            for(int i = 0; i < popSize; i++){
                if (rn.nextInt()%10000 < 1) {
                    demo.mutation(i);
                }
            }
            //Add fittest offspring to population
            demo.newPopulation();
            //Calculate new fitness value
            demo.population.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        }
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
    void selection() {
        //Select the most fittest individual
        fittest = new Population();
        fittest.initializeNewPopulation(population.getParentsPopulation(), population.table);
    } 
    //Crossover
    void crossover() {
        Random rn = new Random();
        //Select a random crossover point
        int crossOverPoint = Math.abs(rn.nextInt(fittest.individuals[0].geneLength));
        //Swap values among parents
        for(int i =0; i < fittest.individuals.length; i+=2){
            for (int j = 0; j < crossOverPoint; j++) {
                int temp = fittest.individuals[i].genes[j];
                fittest.individuals[i].genes[j] = fittest.individuals[i+1].genes[j];
                fittest.individuals[i+1].genes[j]= temp;
            }
        }
    }
    
    //Mutation
    void mutation(int idx) {
        Random rn = new Random();
        //Select a random mutation point
        int mutationPoint = rn.nextInt(fittest.individuals[0].geneLength);
        //Flip values at the mutation point
        if(fittest.individuals[idx].genes[mutationPoint] == 0){
            fittest.individuals[idx].genes[mutationPoint] = 1;
        }else{
            fittest.individuals[idx].genes[mutationPoint] = 0;
        }
    }
    //Get fittest offspring
    Individual getFittestOffspring() {
        return population.getFittest();
    }
    
    //Menggantikan populasi yang lama menjadi populasi yang baru
    void newPopulation() {
        population = new Population();
        population.initializeNewPopulation(fittest.individuals, fittest.table);
    }
}
