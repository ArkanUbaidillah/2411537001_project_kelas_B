package praktikum1;

public class TestCostumer {
    public static void main(String[] args) {
        if (Costumer.login("1", "Arkan")) {
            System.out.println("Login Costumer Berhasil!");
        } else {
            System.out.println("Login Costumer Gagal!");
        }
    }
}
