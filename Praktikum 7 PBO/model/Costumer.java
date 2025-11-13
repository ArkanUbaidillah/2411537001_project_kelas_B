package model;

public class Costumer {

    private String id;
    private String nama;
    private String email;
    private String alamat;
    private String noHp;

    public Costumer(String id, String nama, String email, String alamat, String noHp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.noHp = noHp;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getEmail() { return email; }
    public String getAlamat() { return alamat; }
    public String getNoHp() { return noHp; }
}
