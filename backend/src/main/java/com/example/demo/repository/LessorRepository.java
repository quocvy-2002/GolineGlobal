package com.example.demo.repository;

import com.example.demo.entity.Lessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Integer> {
    boolean existsLessorByLessorName(String lessorName);
    boolean existsLessorByLessorEmail(String lessorEmail);
    Optional<Lessor> findById(Integer lessorId);
    Optional<Lessor> findByLessorEmail(String lessorEmail);
    Optional<Lessor> findByLessorName(String lessorName);

}
