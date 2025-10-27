package Praktikum5;


public interface BahanBakar {
	String jenisBahanBakar();

	default void infoKonsumsi() {
    System.out.println("Info: Konsumsi bahan bakar tergantung kapasitas mesin dan gaya berkendara.");
 }
}

