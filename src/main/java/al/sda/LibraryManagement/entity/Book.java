package al.sda.LibraryManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int year;
    private String isbn;
    private int quantity;
    private int available;
    private int borrowed;
    
    public Book() {
    }
    
    public Book(String title, String author, int year, String isbn, int quantity, int available, int borrowed) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.quantity = quantity;
        this.borrowed = borrowed;
        this.available = available;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getAvailable() {
        return available;
    }
    
    public void setAvailable(int available) {
        this.available = available;
    }
    
    public int getBorrowed() {
        return borrowed;
    }
    
    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }
    
    
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", isbn='" + isbn + '\'' +
                ", quantity=" + quantity +
                ", available=" + available +
                ", borrowed=" + borrowed +
                '}';
    }
}
