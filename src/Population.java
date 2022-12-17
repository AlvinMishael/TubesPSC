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
//Kelas untuk menjadi populasi kromosom
class Population {
    //Variabel dibuat static karena hany perlu diinisialsasi sekali saja
    static int ukuranPopulasi;//Variabel ukuranPopulasi untuk menyimpan banyaknya kromosom untuk setiap populasi
    static int[] tabel;//Variable tabel untuk menyimpan tabel soal yang dimasukan
    static int totalAngkaTabel;//Variable totalAngkaTabel untuk menghitung banyaknya angka yang berada pada tabel soal
    
    Individual[] arrIndividual;//Variabel arrIndividual untuk merepresentasikan populasi 
    int fittest;//Variabel fittest untuk menyimpan fitness terbaik dari sebuah populasi
    int leastFittest;//Variabel leastFittest untuk menyimpan fitness terburuk dari sebuah populasi
    //Metod untuk menginisialisasi populasi baru menggunakan hanya array of Individual
    public void inisialisasiPopulasiBaru(Individual[] arrIndividual){
        this.arrIndividual = new Individual[ukuranPopulasi];
        System.arraycopy(arrIndividual, 0, this.arrIndividual, 0, ukuranPopulasi);
    }

    //Inisialisasi Populasi dari variable ukuran
    public void inisialisasiPopulasi(int size, int geneLength, int[] tabel, int totalAngkaTabel) {
        this.totalAngkaTabel = totalAngkaTabel;
        this.tabel = tabel;
        this.ukuranPopulasi = size;
        this.arrIndividual = new Individual[this.ukuranPopulasi];
        for (int i = 0; i < arrIndividual.length; i++) {
            arrIndividual[i] = new Individual(geneLength, tabel, totalAngkaTabel);
        }
    }

    //Metode untuk mendapatkan individu terbaik
    public Individual individuTerbaik() {
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
    
    //Metode untuk mendapatkan individu terburuk
    public Individual individuTerburuk() {
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
    public void hitungFitness() {
        for (Individual individual : arrIndividual) {
            individual.nilaiFitness();
        }
        this.individuTerbaik();
        this.individuTerburuk();
    }
}
