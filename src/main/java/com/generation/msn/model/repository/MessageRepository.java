package com.generation.msn.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.msn.model.entities.Message;

public interface MessageRepository extends JpaRepository<Message,Integer>
{
	List<Message> findByFriendship_id(int id);
}
