package al.sda.LibraryManagement.service;

import al.sda.LibraryManagement.entity.Book;
import al.sda.LibraryManagement.entity.Loan;
import al.sda.LibraryManagement.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    
    private final BookRepository bookRepository;
    
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public void updateBookAvailabilityOnLoan(Loan loan, String action) {
        Book book = bookRepository.findById(loan.getBook().getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        
        if (loan.getStatus().equals("Borrowed")) {
            if (book.getAvailable() > 0) {
                if (action.equals("DELETE")) {
                    book.setBorrowed(book.getBorrowed() - 1);
                    book.setAvailable(book.getAvailable() + 1);
                } else {
                    book.setBorrowed(book.getBorrowed() + 1);
                    book.setAvailable(book.getAvailable() - 1);
                }
                System.out.println("Book borrowed. Borrowed: " + book.getBorrowed() + ", Available: " + book.getAvailable());
            }
        } else if (loan.getStatus().equals("Returned")) {
            if (book.getBorrowed() > 0) {
                book.setBorrowed(book.getBorrowed() - 1);
                book.setAvailable(book.getAvailable() + 1);
                System.out.println("Book returned. Borrowed: " + book.getBorrowed() + ", Available: " + book.getAvailable());
            }
        }
        
        bookRepository.save(book);
    }
    
    public void addBook(Book book) {
        book.setAvailable(book.getQuantity());  // Të gjithë librat janë të disponueshëm
        book.setBorrowed(0);  // Asnjë libër nuk është huazuar akoma
        
        bookRepository.save(book);  // Shto libër në DB
    }
    
    
    public void updateBookQuantity(Book book) {
        // Merr librin nga DB për të kontrolluar gjendjen e tij aktuale
        Book bookFromDb = bookRepository.findById(book.getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        System.out.println("Book from DB: " + bookFromDb);
        System.out.println("Book from form: " + book);
        int quantityDifference = book.getQuantity() - bookFromDb.getQuantity();
        
        int newAvailable = book.getAvailable() + quantityDifference;
        System.out.println("New available: " + newAvailable);
        book.setAvailable(newAvailable);
        book.setBorrowed(book.getBorrowed());
        
        if (newAvailable < 0) {
            book.setAvailable(0);
        }
        
        bookRepository.save(book);  // Përshtatni librin në bazën e të dhënave
    }
    
    
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(new Book());
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
}
