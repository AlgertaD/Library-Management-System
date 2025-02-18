package al.sda.LibraryManagement.entity;

import jakarta.persistence.*;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    Book book;
    @ManyToOne
    Student student;
    private String loanDate;
    private String returnDate;
    private String status;
    
    public Loan() {
    }
    
    public Loan(Book book, Student student, String loanDate, String returnDate, String status) {
        this.book = book;
        this.student = student;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Book getBook() {
        return book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public String getLoanDate() {
        return loanDate;
    }
    
    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }
    
    public String getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", book=" + book +
                ", student=" + student +
                ", loanDate='" + loanDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
