package com.generation.msn.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.msn.model.entities.User;

public interface UserRepository extends JpaRepository <User, Integer>
{
	User findByMail(String mail);
}
