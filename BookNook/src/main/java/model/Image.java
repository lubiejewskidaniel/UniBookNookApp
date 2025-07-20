package model;

public class Image {
    private int id;
    private int bookId;
    private String imagePath;

    public Image(int id, int bookId, String imagePath) {
        this.id = id;
        this.bookId = bookId;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}