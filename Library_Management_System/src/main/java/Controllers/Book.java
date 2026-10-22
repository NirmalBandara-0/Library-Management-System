
package Controllers;

public class Book {

    private String bookId;
    private String bookName;
    private String author;
    private String category;
    private String publishedYear;
    private int quantity;
    private int availableQuantity;

    public Book(String bookId, String bookName, String author,
                String category, String publishedYear, int quantity) {

        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.publishedYear = publishedYear;
        this.quantity = quantity;
        this.availableQuantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public String toString() {
        return bookId + " - " + bookName;
    }
}
