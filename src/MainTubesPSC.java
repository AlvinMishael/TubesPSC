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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Ukuran Populasi Awal: ");
        int ukuranPopulasi = sc.nextInt();
        System.out.print("Ukuran tabel: ");
        int panjangTabel = sc.nextInt();
        //Menginisialisi panjangTabel dengan kuadrat dari input, karena untuk memetakan array 2 dimensi ke 1 dimensi maka panjang tabel 1 dimensi merupakan kuadrat dari
        //panjang array 2 dimensi (contoh: array 2 dimensi dengan ukuran 5x5 diubah menjadi array 1 dimensi memerlukan ukuran 25
        panjangTabel = panjangTabel *panjangTabel;
        int[] tabel = new int[panjangTabel];
        System.out.println("Masukan tabel: (-1 = kotak kosong)");
        int totalAngkaTabel =0; //variable ini digunakan untuk menghitung kotak yg tidak kosong atau terisi angka
        for(int i =0; i < panjangTabel; i++){ //input tabel mineswipper
            tabel[i] = sc.nextInt();
            if(tabel[i] != -1){ //setiap ada input kotak yg berisi angka
                totalAngkaTabel ++; //update jumlahnya
            }
        }
        Generasi generasi = new Generasi(ukuranPopulasi, totalAngkaTabel, panjangTabel, tabel); //inisiasi generasi baru
        do{ //lakukan pencarian generasi baru untuk menemukan hasil selama fittest masih kurang dari
            generasi.populasi.hitungFitness(); //menghitung fittest dari generasi baru
            System.out.println("Generasi ke-" + generasi.generationCount + " Fitness terbesar: " + generasi.populasi.fittest +" Fitness terkecil: " +generasi.populasi.leastFittest + " Fitness benar: " + generasi.totalAngkaTabel*9*10);
            generasi.generationCount++; //update jumlah generasi
            if(generasi.populasi.fittest < generasi.totalAngkaTabel*10*9){ //selama belum menemukan hasil yang optimal,
                generasi.seleksiParent(); //lakukan pencarian parent
                generasi.crossover(); //kemudian crossover
                //dan kemungkinan terjadinya mutasi dalam membentuk generasi baru
                for(int i = 0; i < generasi.ukuranPopulasi; i++){
                    if (rand.nextInt(10000) < 1) {
                        generasi.mutation(i);
                    }
                }
                generasi.generasiBerikut();
            }
        }while (generasi.populasi.fittest < generasi.totalAngkaTabel * 10*9);
        System.out.println("Generasi terbaik berada pada generasi populasi ke-" + generasi.generationCount);
        System.out.println("Dengan Fitness: "+ generasi.populasi.fittest);
        System.out.println("Genes solusi: ");
        int len = (int) Math.sqrt(panjangTabel);
        for (int i = 0; i < len; i++) {
            for(int j =0; j < len; j++){
                System.out.print(generasi.populasi.individuTerbaik().arrGene[i*len + j] + " ");
            }
            System.out.println("");
        }
    }
}
