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
        int maxfittest = Integer.MIN_VALUE; //set maximum fittest dengan negatif infinit
        int idx_maxfittest = 0; //index awal 0
        for (int i = 0; i < arrIndividual.length; i++) { //loop sepanjang array untuk mencari fittest tertinggi
            if (maxfittest <= arrIndividual[i].fitness) { //cek fittest individu ke-i lebih besar dari maxfittest saat ini
                maxfittest = arrIndividual[i].fitness; //jika lebih besar, update maxfittest
                idx_maxfittest = i; //update index individu yang punya maxfittest
            }
        }
        fittest = arrIndividual[idx_maxfittest].fitness; //update fittest
        return arrIndividual[idx_maxfittest]; //kembalikan individu dengan fittest tertinggi
    }
    
    //Metode untuk mendapatkan individu terburuk
    public Individual individuTerburuk() {
        int minFittest = Integer.MAX_VALUE; //set minimum fittest dengan positif infinit
        int idx_minFittest = 0; //index awal 0
        for (int i = 0; i < arrIndividual.length; i++) { //loop sepanjang array untuk mencari fittest terendah
            if (minFittest >= arrIndividual[i].fitness) { //jika fittest individu ke-1 lebih kecil dari minFittest saat ini
                minFittest = arrIndividual[i].fitness; //update minfittest
                idx_minFittest = i; //update index individu yang punya minfittest
            }
        }
        leastFittest = arrIndividual[idx_minFittest].fitness; //update leastfittest
        return arrIndividual[idx_minFittest]; //kembalikan individu dengan fittest terendah
    }
    
    public Individual[] getarrParentPopulation(){
        Individual[] arrParent = new Individual[this.ukuranPopulasi]; //inisiasi array of individu untuk menyimpan parent
        for(int i =0; i < this.ukuranPopulasi; i++){ //loop sebanyak ukuran populasi
            arrParent[i] = new Individual(this.rouletteWheelSelecetion()); //setiap parent merupakan parent baru yang didapatkan dari selection menggunakan roulette
        }
        return arrParent; 
    }
    
    //method roulette wheel untuk melakukan selection mencari parent dari populasi
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

    //menghitung fitness setiap individu
    public void hitungFitness() {
        for (Individual individual : arrIndividual) {
            individual.nilaiFitness();
        }
        this.individuTerbaik();
        this.individuTerburuk();
    }
}
