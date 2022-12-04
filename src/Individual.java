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
//Individual class
class Individual {

    int fitness;
    int geneLength;
    int[] genes;
    int[] tabel;
    
    public Individual(Individual curr){
        this.geneLength = curr.geneLength;
        this.fitness = curr.fitness;
        this.genes = new int[curr.genes.length];
        System.arraycopy(curr.genes, 0, this.genes, 0, curr.genes.length);
        this.tabel = new int[curr.tabel.length];
        System.arraycopy(curr.tabel, 0, this.tabel, 0, curr.tabel.length);
    }
    

    public Individual(int geneLength, int[] tabel) {
        Random rn = new Random();
        this.geneLength = geneLength;
        this.genes = new int[geneLength];
        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }
        this.tabel = new int[tabel.length];
        System.arraycopy(tabel, 0, this.tabel, 0, tabel.length);
        fitness = 100;
    }
    
    public void setTable(int[] tabel){
        this.tabel = new int[tabel.length];
        System.arraycopy(tabel, 0, this.tabel, 0, tabel.length);
    }

    //Calculate fitness
    public void calcFitness() {
        fitness = 100;
        int len = (int)Math.sqrt(tabel.length);
        for (int i = 0; i < len; i++) {
           for(int j =0; j < len;j++){
               if(tabel[((i*len) + j)] != -1){
                   int counter = 0;
                   if(i != 0 && i != (len-1)){
                       if(j != 0 && j != (len-1)){
                           for(int k = i-1; k <= i +1; k++){
                               for(int p = j-1; p <= j +1; p++){
                                   if(genes[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == 0){
                           for(int k = i-1; k <= i +1 ; k++){
                               for(int p = j; p <= j+1; p++){
                                   if(genes[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == (len-1)){
                           for(int k = i-1; k <= i +1 ; k++){
                               for(int p = j-1; p <= j; p++){
                                   if(genes[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }
                   }else if(i == 0){
                       if(j != 0 && j != (len-1)){
                           for(int k = i; k <= i +1; k++){
                               for(int p = j-1; p <= j +1; p++){
                                   if(genes[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == 0){
                           for(int k = i; k <= i +1 ; k++){
                               for(int p = j; p <= j+1; p++){
                                   if(genes[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == (len-1)){
                           for(int k = i; k <= i +1 ; k++){
                               for(int p = j-1; p <= j; p++){
                                   if(genes[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }
                   }else if(i == (len-1)){
                       if(j != 0 && j != (len-1)){
                           for(int k = i-1; k <= i ; k++){
                               for(int p = j-1; p <= j +1; p++){
                                   if(genes[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == 0){
                           for(int k = i-1; k <= i  ; k++){
                               for(int p = j; p <= j+1; p++){
                                   if(genes[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == (len-1)){
                           for(int k = i-1; k <= i ; k++){
                               for(int p = j-1; p <= j; p++){
                                   if(genes[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }
                   }
                   int beda = Math.abs(counter-tabel[((i*len) + j)]);
                   this.fitness -= beda;
               }
           }
        }
    }
}
