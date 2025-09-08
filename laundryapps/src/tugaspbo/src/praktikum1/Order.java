package praktikum1;


import java.util.Date;

public class Order {
    private int id;
    private int idCostumer;
    private int idService;
    private int idUser;
    private double total;
    private Date tanggal;
    private Date tanggalSelesai;
    private String status;
    private String statusPembayaran;

    // Constructor
    public Order(int id, int idCostumer, int idService, int idUser, double total,
                 Date tanggal, Date tanggalSelesai, String status, String statusPembayaran) {
        this.id = id;
        this.idCostumer = idCostumer;
        this.idService = idService;
        this.idUser = idUser;
        this.total = total;
        this.tanggal = tanggal;
        this.tanggalSelesai = tanggalSelesai;
        this.status = status;
        this.statusPembayaran = statusPembayaran;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCostumer() {
        return idCostumer;
    }

    public void setIdCostumer(int idCostumer) {
        this.idCostumer = idCostumer;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    // Menampilkan informasi order
    public void tampilkanInfo() {
        System.out.println("ID Order: " + id);
        System.out.println("Costumer ID: " + idCostumer);
        System.out.println("Service ID: " + idService);
        System.out.println("User ID: " + idUser);
        System.out.println("Total: Rp" + total);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Tanggal Selesai: " + tanggalSelesai);
        System.out.println("Status: " + status);
        System.out.println("Status Pembayaran: " + statusPembayaran);
    }
}
