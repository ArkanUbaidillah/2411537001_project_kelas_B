package model;

public class Book {
    private String id;
    private String title;
    private String author;
    private int year;

    private Book(BookBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.author = builder.author;
        this.year = builder.year;
    }

    public static class BookBuilder {
        private String id;
        private String title;
        private String author;
        private int year;

        public BookBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public BookBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
}
