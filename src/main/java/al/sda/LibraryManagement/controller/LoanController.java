package al.sda.LibraryManagement.controller;

import al.sda.LibraryManagement.entity.Loan;
import al.sda.LibraryManagement.entity.Student;
import al.sda.LibraryManagement.service.BookService;
import al.sda.LibraryManagement.service.LoanService;
import al.sda.LibraryManagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loans")
public class LoanController {
    
    private final LoanService loanService;
    private final StudentService studentService;
    private final BookService bookService;
    
    public LoanController(LoanService loanService, StudentService studentService, BookService bookService) {
        this.loanService = loanService;
        this.studentService = studentService;
        this.bookService = bookService;
    }
    
    @GetMapping("/list")
    public String listLoans(Model model) {
        model.addAttribute("loans", loanService.getAllLoans());
        return "loans";
    }
    
    @GetMapping("/addLoan")
    public String addLoan(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("loan", new Loan());
        return "addLoan";
    }
    
    @PostMapping("/addLoan")
    public String saveLoan(@ModelAttribute Loan loan) {
        bookService.updateBookAvailabilityOnLoan(loan,"ADD");
        loanService.saveLoan(loan);
        return "redirect:/loans/list";
    }
    
    @GetMapping("/update/{id}")
    public String updateLoan(@PathVariable Long id, Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("loan", loanService.getLoanById(id));
        return "updateLoan";
    }
    
    @PostMapping("/update/{id}")
    public String updateLoan(@PathVariable Long id, @ModelAttribute Loan loan) {
        loan.setId(id);
        bookService.updateBookAvailabilityOnLoan(loan,"UPDATE");
        loanService.saveLoan(loan);
        return "redirect:/loans/list";
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteLoan(@PathVariable Long id) {
        Loan loan = loanService.getLoanById(id);
        bookService.updateBookAvailabilityOnLoan(loan,"DELETE");
        loanService.deleteLoan(id);
        return "redirect:/loans/list";
    }
}
