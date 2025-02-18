package al.sda.LibraryManagement.service;

import al.sda.LibraryManagement.entity.Student;
import al.sda.LibraryManagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public void addStudent(Student student) {
        studentRepository.save(student);
    }
    
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(new Student());
    }
}