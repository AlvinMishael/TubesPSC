/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alvin
 */
/*
referensi:
https://gist.github.com/Vini2/bd22b36ddc69c5327097921f5118b709 untuk kode genetic algorithm

https://www.youtube.com/watch?v=9JzFcGdpT8E untuk roulette wheel selection

https://linuxhint.com/sort-2d-array-in-java/ untuk sorting array 2d
*/
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class MainTubesPSC {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Path nama file input: ");
        String namaFile = sc.next();
        FileInputStream fis = new FileInputStream(namaFile);
        Scanner fileReader = new Scanner(fis);
        long seedRandom = fileReader.nextLong();//Membuat variabel untuk variabel random
        Random rand = new Random(seedRandom);
        int ukuranPopulasi = fileReader.nextInt();//Menginisialisasi variabel untuk ukuran populasi
        int panjangTabel = fileReader.nextInt();//Menginisialisasi variabel untuk panjang tabel
        //Menginisialisi panjangTabel dengan kuadrat dari input, karena untuk memetakan array 2 dimensi ke 1 dimensi maka panjang tabel 1 dimensi merupakan kuadrat dari
        //panjang array 2 dimensi (contoh: array 2 dimensi dengan ukuran 5x5 diubah menjadi array 1 dimensi memerlukan ukuran 25
        panjangTabel = panjangTabel *panjangTabel;
        int batasGenerasi = fileReader.nextInt();//Menginisialisasi variabel untuk batas generasi yang akan dicoba
        int probabilitasMutasi = fileReader.nextInt();//Menginisialisasi variabel untuk probabilitas mutasi (probabilitas akhir akan menjadi 1/probabilitasMutasi)
        int[] tabel = new int[panjangTabel];//Membuat variabel untuk menyimpan soal pada array of int
        int pilihanSelection = fileReader.nextInt();//Membuat variabel untuk memilih selection apa yang akan dilakukan 
        int pilihanCrossover = fileReader.nextInt();//Membuat variabel untuk memilih crossover apa yang akan dilakukan 
        int banyakElitism = fileReader.nextInt();//Membaut variabel untuk menentukan berapa banyak individu yang akan dipilih dalam proses elitism
        int totalAngkaTabel =0; //variable ini digunakan untuk menghitung kotak yg tidak kosong atau terisi angka
        for(int i =0; i < panjangTabel; i++){ //input tabel mineswipper
            tabel[i] = fileReader.nextInt();
            if(tabel[i] != -1){ //setiap ada input kotak yg berisi angka
                totalAngkaTabel ++; //update jumlahnya
            }
        }
        fileReader.close();
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(namaFile.substring(namaFile.length()-4)+ "Res.txt"));
        long generationCount = 1; //Membuat atribut untuk menghitung generasi
        Generasi generasi = new Generasi(ukuranPopulasi, totalAngkaTabel, panjangTabel, tabel, rand, banyakElitism); //inisiasi generasi baru
        do{ //lakukan pencarian generasi baru untuk menemukan hasil selama fittest masih kurang dari
            generasi.populasi.hitungFitness(); //menghitung fittest dari generasi baru
            fileWriter.write("Generasi ke-" + generationCount + " Fitness terbesar: " + generasi.populasi.fittest +" Fitness terkecil: " +generasi.populasi.leastFittest + " Fitness benar: " + generasi.totalAngkaTabel*9*10);
            fileWriter.append("\n");
            generationCount++; //update jumlah generasi
            if(generasi.populasi.fittest < generasi.totalAngkaTabel*10*9){ //selama belum menemukan hasil yang optimal,
                generasi.seleksiParent(pilihanSelection); //lakukan pencarian parent
                generasi.crossover(pilihanCrossover); //kemudian crossover
                //dan kemungkinan terjadinya mutasi dalam membentuk generasi baru adalah 1/10000 untuk setiap anggota dalam populasi
                for(int i = 0; i < generasi.ukuranPopulasi; i++){
                    if (rand.nextInt(probabilitasMutasi) < 1) {//Jika masuk kedalam kemungkinan mutaasi
                        generasi.mutation(i);//Maka lakukan mutasi untuk individu tersebut
                    }
                }
                generasi.generasiBerikut();//Melakukan inisialisasi generasi berikutnya menggunakan method generasi
            }
        }while (generasi.populasi.fittest < generasi.totalAngkaTabel * 10*9 && generationCount < batasGenerasi);//Looping akan diulang sampai solusi sudah ketemu atau generasi sudah mencapai batas
        if(generasi.populasi.fittest == (generasi.totalAngkaTabel*10*9)){//Jika solusi ketemu maka keluarkan output yang sesuai
            fileWriter.write("Generasi solusi berada pada generasi populasi ke-" + generationCount);
        }else{//Jika tidak maka solusi hanya sub-optimal
            fileWriter.write("Pencobaan generasi berhenti pada generasi populasi ke-" + generationCount);
        }
        fileWriter.append("\n");
        //Output hasil akhir
        fileWriter.write("Fitness akhir: "+ generasi.populasi.fittest + "/"+generasi.totalAngkaTabel*10*9);
        fileWriter.append("\n");
        fileWriter.write("Genes terbaik: ");
        fileWriter.append("\n");
        int len = (int) Math.sqrt(panjangTabel);//Mengambil akar dari panjang untuk memetakan output pada array 2D
        //Mengeluarkan output
        for (int i = 0; i < len; i++) {
            for(int j =0; j < len; j++){
                fileWriter.write(generasi.populasi.individuTerbaik().arrGene[i*len + j] + " ");
            }
            fileWriter.append("\n");
        }
    }
}
