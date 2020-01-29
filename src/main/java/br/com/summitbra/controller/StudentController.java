package br.com.summitbra.controller;

import br.com.summitbra.error.CustomErrorType;
import br.com.summitbra.model.Student;
import br.com.summitbra.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController {


    private final StudentRepository studentDAO;

    @Autowired
    public StudentController(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    private void clear(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("https://rubenstakauti-eval-test.apigee.net/students/clear-cache", String.class);
    }

    @GetMapping(path = "/clear-cache")
    public ResponseEntity<?> clearCache() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        Optional<Student> student = studentDAO.findById(id);
        if (student.isPresent()) return new ResponseEntity<>(student, HttpStatus.OK);
        return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {
        clear();
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clear();
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        clear();
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
