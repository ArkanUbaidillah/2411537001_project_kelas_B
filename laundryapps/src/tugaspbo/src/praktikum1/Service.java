package praktikum1;

public class Service {
    private int id;
    private String jenis,status;
    private double harga;
    

    // Constructor
    public Service(int id, String jenis, double harga, String status) {
        this.id = id;
        this.jenis = jenis;
        this.harga = harga;
        this.status = status;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
    public void tampilkanInfo() {
        System.out.println("ID: " + id);
        System.out.println("Jenis: " + jenis);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Status: " + status);
    }
}
