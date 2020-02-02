package br.com.rtakauti.repository;

import br.com.rtakauti.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByName(String name);

}
