package al.sda.LibraryManagement.service;

import al.sda.LibraryManagement.entity.Loan;
import al.sda.LibraryManagement.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    
    
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
    
    public void saveLoan(Loan loan) {
        loanRepository.save(loan);
    }
    
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElse(new Loan());
    }
    
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
    
    
    
}
