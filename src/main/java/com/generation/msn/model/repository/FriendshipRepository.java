package com.generation.msn.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.msn.model.entities.Friendship;
import com.generation.msn.model.entities.User;


public interface FriendshipRepository  extends JpaRepository <Friendship, Integer>
{
	Friendship findByUser1AndUser2(User user1, User user2);

}
