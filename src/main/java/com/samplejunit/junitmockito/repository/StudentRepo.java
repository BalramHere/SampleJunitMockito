package com.samplejunit.junitmockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samplejunit.junitmockito.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
	
}

