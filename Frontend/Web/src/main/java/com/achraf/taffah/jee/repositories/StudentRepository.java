package com.achraf.taffah.jee.repositories;

import com.achraf.taffah.jee.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findByFirstNameContains(String keyword, Pageable pageable);

}
