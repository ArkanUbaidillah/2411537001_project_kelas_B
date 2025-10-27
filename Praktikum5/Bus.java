package Praktikum5;

public class Bus extends Kendaraan implements TransportasiUmum {
    private String kelasBus;
    private int kapasitasPenumpang;

    public Bus(String merk, String model, int tahunProduksi, String kelasBus, int kapasitasPenumpang) {
        super(merk, model, tahunProduksi);
        this.kelasBus = kelasBus;
        this.kapasitasPenumpang = kapasitasPenumpang;
    }

    @Override
    public void nyalakanMesin() {
        System.out.println("Nyalakan Mesin: Putar kunci untuk menyalakan");
    }

    @Override
    public String jenisBahanBakar() {
        return "Solar";
    }

    @Override
    public int kapasitasPenumpang() {
        return kapasitasPenumpang;
    }

    public void fiturBus() {
        System.out.println("Fitur Bus: Dilengkapi kursi nyaman dan fasilitas hiburan");
    }

    // Inner class
    public class JadwalPerjalanan {
        private String rute;
        private String waktuBerangkat;

        public JadwalPerjalanan(String rute, String waktuBerangkat) {
            this.rute = rute;
            this.waktuBerangkat = waktuBerangkat;
        }

        public void tampilkanJadwal() {
            System.out.println("Jadwal Perjalanan: Rute " + rute + ", Waktu Berangkat: " + waktuBerangkat);
        }
    }
}
