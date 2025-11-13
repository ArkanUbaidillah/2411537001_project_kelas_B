package model;

public class CustomerBuilderTest {
    public static void main(String[] args) {
        CustomerBuilder b = new CustomerBuilder()
                .setId("1")
                .setNama("Budi")
                .setEmail("budi@example.com")
                .setAlamat("Jalan")
                .setNoHp("0812345678");
        Costumer c = b.build();
        System.out.println("Nama: " + c.getNama());
        System.out.println("Email: " + c.getEmail());
    }
}
