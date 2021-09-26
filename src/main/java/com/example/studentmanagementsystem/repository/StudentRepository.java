package com.example.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentmanagementsystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
