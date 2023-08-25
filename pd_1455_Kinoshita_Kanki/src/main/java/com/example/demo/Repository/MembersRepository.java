package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Members;

public interface MembersRepository extends JpaRepository<Members, Integer> {

	List<Members> findByEmailAndPassword(String email, String password);

}
