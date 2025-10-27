package Praktikum5;

public class Pesawat extends Kendaraan implements TransportasiUdara, Maskapai {
    private String jenisPenerbangan;
    private String namaMaskapai;

    public Pesawat(String merk, String model, int tahunProduksi, String namaMaskapai, String jenisPenerbangan) {
        super(merk, model, tahunProduksi);
        this.namaMaskapai = namaMaskapai;
        this.jenisPenerbangan = jenisPenerbangan;
    }

    @Override
    public void nyalakanMesin() {
        System.out.println("Nyalakan Mesin: Bersiap lepas landas");
    }

    @Override
    public String jenisBahanBakar() {
        return "Avtur";
    }

    @Override
    public String jenisPenerbangan() {
        return jenisPenerbangan;
    }

    @Override
    public String namaMaskapai() {
        return namaMaskapai;
    }
}
