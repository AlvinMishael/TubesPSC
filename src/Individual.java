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
    int[] arrGene;
    int[] tabel;
    
    public Individual(Individual curr){
        this.geneLength = curr.geneLength;
        this.fitness = curr.fitness;
        this.arrGene = new int[curr.arrGene.length];
        System.arraycopy(curr.arrGene, 0, this.arrGene, 0, curr.arrGene.length);
        this.tabel = new int[curr.tabel.length];
        System.arraycopy(curr.tabel, 0, this.tabel, 0, curr.tabel.length);
    }
    

    public Individual(int geneLength, int[] tabel) {
        Random rand = new Random();
        this.geneLength = geneLength;
        this.arrGene = new int[geneLength];
        //Set arrGene randomly for each individual
        for (int i = 0; i < arrGene.length; i++) {
            arrGene[i] = Math.abs(rand.nextInt() % 2);
        }
        this.tabel = new int[tabel.length];
        System.arraycopy(tabel, 0, this.tabel, 0, tabel.length);
        fitness = 100;
    }
    
    public void setTabel(int[] tabel){
        this.tabel = new int[tabel.length];
        System.arraycopy(tabel, 0, this.tabel, 0, tabel.length);
    }

    //Calculate fitness
    public void nilaiFitness() {
        int counter = 0;
        int angkaSalah = 0;
        fitness = tabel.length*tabel.length*9;
        int len = (int)Math.sqrt(tabel.length);
        for (int i = 0; i < len; i++){
           for(int j =0; j < len;j++){
               if(tabel[((i*len) + j)] != -1){
                   counter = 0;
                   if(i != 0 && i != (len-1)){
                       if(j != 0 && j != (len-1)){
                           for(int k = i-1; k <= i +1; k++){
                               for(int p = j-1; p <= j +1; p++){
                                   if(arrGene[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == 0){
                           for(int k = i-1; k <= i +1 ; k++){
                               for(int p = j; p <= j+1; p++){
                                   if(arrGene[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == (len-1)){
                           for(int k = i-1; k <= i +1 ; k++){
                               for(int p = j-1; p <= j; p++){
                                   if(arrGene[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }
                   }else if(i == 0){
                       if(j != 0 && j != (len-1)){
                           for(int k = i; k <= i +1; k++){
                               for(int p = j-1; p <= j +1; p++){
                                   if(arrGene[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == 0){
                           for(int k = i; k <= i +1 ; k++){
                               for(int p = j; p <= j+1; p++){
                                   if(arrGene[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == (len-1)){
                           for(int k = i; k <= i +1 ; k++){
                               for(int p = j-1; p <= j; p++){
                                   if(arrGene[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }
                   }else if(i == (len-1)){
                       if(j != 0 && j != (len-1)){
                           for(int k = i-1; k <= i ; k++){
                               for(int p = j-1; p <= j +1; p++){
                                   if(arrGene[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == 0){
                           for(int k = i-1; k <= i  ; k++){
                               for(int p = j; p <= j+1; p++){
                                   if(arrGene[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }else if(j == (len-1)){
                           for(int k = i-1; k <= i ; k++){
                               for(int p = j-1; p <= j; p++){
                                   if(arrGene[k*len + p] == 1){
                                       counter++;
                                   }
                               }
                           }
                       }
                   }
                   int beda = Math.abs(counter-tabel[((i*len) + j)]);
                   if(beda != 0){
                       angkaSalah++;
                       counter += beda;
                   }
               }
           }
        }
        this.fitness  = this.fitness - (angkaSalah * counter);
    }
}
