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
    

    public Individual(int geneLength) {
        Random rn = new Random();
        this.geneLength = geneLength;
        this.genes = new int[geneLength];
        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }
        fitness = 100;
    }

    //Calculate fitness
    public void calcFitness() {
        fitness = 100;
        for (int i = 0; i < 25; i++) {
           
        }
    }

}
