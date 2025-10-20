package Praktikum4;

public class TextBook extends Book {
    private String bidangStudi;

    public TextBook(String title, String author, String bidangStudi) {
        super(title, author);
        this.bidangStudi = bidangStudi;
    }

    public String getBidangStudi() { return bidangStudi; }
}
