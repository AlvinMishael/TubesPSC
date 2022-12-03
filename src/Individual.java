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

    int fitness = 0;
    int[] genes = new int[25];
    int geneLength = 25;

    public Individual() {
        Random rn = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }
        fitness = 100;
    }

    //Calculate fitness
    public void calcFitness() {
        fitness = 100;
        for (int i = 0; i < 5; i++) {
           
        }
    }

}
