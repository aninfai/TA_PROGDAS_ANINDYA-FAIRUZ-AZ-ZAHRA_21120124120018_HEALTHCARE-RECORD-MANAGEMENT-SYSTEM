import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class KasirAntrean {
    private Queue<String> antrean;
    private Scanner scanner;

    public KasirAntrean() {
        antrean = new LinkedList<>();
        scanner = new Scanner(System.in);
    }

    public void menu() {
        int pilihan;
        do {
            System.out.println("\n=== Menu Antrean Kasir ===");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Status Antrean");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // membersihkan buffer

            switch (pilihan) {
                case 1:
                    tambahPelanggan();
                    break;
                case 2:
                    layaniPelanggan();
                    break;
                case 3:
                    tampilkanStatusAntrean();
                    break;
                case 4:
                    System.out.println("Terima kasih! Selamat tinggal.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 4);
    }

    private void tambahPelanggan() {
        System.out.print("Masukkan nama pelanggan: ");
        String nama = scanner.nextLine();
        antrean.add(nama);
        System.out.println(nama + " telah ditambahkan ke antrean.");
    }

    private void layaniPelanggan() {
        if (antrean.isEmpty()) {
            System.out.println("Tidak ada pelanggan dalam antrean.");
        } else {
            String pelangganDilayani = antrean.poll();
            System.out.println("Melayani pelanggan: " + pelangganDilayani);
        }
    }

    private void tampilkanStatusAntrean() {
        if (antrean.isEmpty()) {
            System.out.println("Antrean kosong.");
        } else {
            System.out.println("Status antrean: " + antrean);
        }
    }

    public static void main(String[] args) {
        KasirAntrean kasir = new KasirAntrean();
        kasir.menu();
    }
}