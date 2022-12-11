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
    Population populasi = new Population();   //Men-generate populasi 
    Population fitness;                         
    int ukuranPopulasi;
    long generationCount = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        MainTubesPSC demo = new MainTubesPSC();
        System.out.print("Ukuran Populasi Awal: ");
        int ukuranPopulasi = sc.nextInt();
        System.out.print("Ukuran tabel: ");
        int panjangTabel = sc.nextInt();
        int[] tabel = new int[panjangTabel*panjangTabel];
        System.out.println("Masukan tabel: ");
        for(int i =0; i < panjangTabel*panjangTabel; i++){
            tabel[i] = sc.nextInt();
        }
        //Initialize population
        demo.populasi.inisialisasiPopulasi(ukuranPopulasi, panjangTabel*panjangTabel, tabel);
        //While population gets an individual with maximum fitness
        do{
            //Calculate new fitness value
            demo.populasi.hitungFittest();
            System.out.println("Generation: " + demo.generationCount + " Fitness: " + demo.populasi.fittest +" Least Fitness: " +demo.populasi.leastFittest);
            demo.generationCount++;
            if(demo.populasi.fittest < panjangTabel*panjangTabel*panjangTabel*panjangTabel*9){
                //Do selection
                demo.selection();
                //Do crossover
                demo.crossover();
                //Do mutation under a random probability
                for(int i = 0; i < ukuranPopulasi; i++){
                    if (rand.nextInt(10000) < 1) {
                        demo.mutation(i);
                    }
                }
                //Add fittest offspring to population
                demo.newPopulation();
            }
        }while (demo.populasi.fittest < panjangTabel*panjangTabel*panjangTabel*panjangTabel*9);
        System.out.println("\n Jumlah Solusi " + demo.generationCount);
        System.out.println("Fitness: "+ demo.populasi.getFittest().fitness);
        System.out.println("genes: ");
        for (int i = 0; i < panjangTabel; i++) {
            for(int j =0; j < panjangTabel; j++){
                System.out.print(demo.populasi.getFittest().arrGene[i*panjangTabel + j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    //Selection
    public void selection() {
        //Select the most fittest individual
        fitness = new Population();
        fitness.inisialisasiPopulasiBaru(populasi.getarrParentPopulation(), populasi.table);
    } 
    //Crossover
    public void crossover() {
        Random rand = new Random();
        //Select a random crossover point
        int crossOverPoint = Math.abs(rand.nextInt(fitness.arrIndividual[0].geneLength));
        //Swap values among parents
        for(int i =0; i < fitness.arrIndividual.length; i+=2){
            for (int j = 0; j < crossOverPoint; j++) {
                int temp = fitness.arrIndividual[i].arrGene[j];
                fitness.arrIndividual[i].arrGene[j] = fitness.arrIndividual[i+1].arrGene[j];
                fitness.arrIndividual[i+1].arrGene[j]= temp;
            }
        }
    }
    
    //Mutation
    public void mutation(int idx) {
        Random rand = new Random();
        //Select a random mutation point
        int mutationPoint = rand.nextInt(fitness.arrIndividual[0].geneLength);
        //Flip values at the mutation point
        if(fitness.arrIndividual[idx].arrGene[mutationPoint] == 0){
            fitness.arrIndividual[idx].arrGene[mutationPoint] = 1;
        }else{
            fitness.arrIndividual[idx].arrGene[mutationPoint] = 0;
        }
    }
    //Get fittest offspring
    public Individual getFitnessOffspring() {
        return populasi.getFittest();
    }
    
    //Menggantikan populasi yang lama menjadi populasi yang baru
    public void newPopulation() {
        populasi = new Population();
        populasi.inisialisasiPopulasiBaru(fitness.arrIndividual, fitness.table);
    }
}
