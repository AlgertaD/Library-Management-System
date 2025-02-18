package al.sda.LibraryManagement.controller;

import al.sda.LibraryManagement.entity.Book;
import al.sda.LibraryManagement.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    
    private final BookService bookService;
    
    
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/list")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }
    
    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }
    
    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books/list";
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books/list";
    }
    
    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "updateBook";
    }
    
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.updateBookQuantity(book);
        return "redirect:/books/list";
    }
    
    @GetMapping("/searchBooks")
    public String searchBooks(Model model) {
        model.addAttribute("searchText", "");
        model.addAttribute("books", Collections.emptyList());
        model.addAttribute("isEmpty", true);
        return "searchBooks";
    }
    
    @GetMapping("/search")
    public String getAllBooks(
            @RequestParam(value = "searchBy", required = false, defaultValue = "title") String searchBy,
            @RequestParam(value = "searchText", required = false) String searchText,
            Model model) {
        
        List<Book> books;
        
        if (searchText != null && !searchText.isEmpty()) {
                books = bookService.searchBooksByTitle(searchText);
            model.addAttribute("searchText", searchText);
            model.addAttribute("searchBy", searchBy);
        } else {
            books = bookService.getAllBooks();
            model.addAttribute("searchText", "");
            model.addAttribute("searchBy", "title");
        }
        model.addAttribute("books", books);
        model.addAttribute("isEmpty", books.isEmpty());
        return "searchBooks";
    }
    
    
}
