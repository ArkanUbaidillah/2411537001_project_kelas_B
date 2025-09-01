package praktikum1;

public class Costumer {
    String id, nama, alamat, nomorHp;

    

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorHp() {
        return nomorHp;
    }
    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

  

    public static boolean login(String id, String nama) {
        boolean isLoggin = false;

        Costumer costumer = new Costumer();
        costumer.setId("1");
        costumer.setNama("Arkan");
        costumer.setAlamat("Padang");
        costumer.setNomorHp("087842184809");
       

        if (costumer.getId().equalsIgnoreCase(id) &&
            costumer.getNama().equalsIgnoreCase(nama)) {
            isLoggin = true;
        } else {
            isLoggin = false;
        }

        return isLoggin;
    }
}
