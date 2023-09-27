package com.generation.msn.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.generation.msn.library.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseEntity
{
	private String nickname,mail,password;
	private String profile_img;
	
	@OneToMany(mappedBy = "user1", fetch = FetchType.EAGER)
	List<Friendship> friendshipsSender = new ArrayList<Friendship>(); 
	@OneToMany(mappedBy = "user2", fetch = FetchType.EAGER)
	List<Friendship> friendshipsReciver=new ArrayList<Friendship>();
	
	@ManyToMany
	@JoinTable(name="user_to_group" , joinColumns= @JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="group_chat_id"))
	List<GroupChat> groups = new ArrayList<GroupChat>();
	
	
	
	
	public List<Friendship> getAllFriendship()
	{
		List<Friendship> allFriendship = new ArrayList<Friendship>();
		
		allFriendship.addAll(friendshipsReciver);
		allFriendship.addAll(friendshipsSender);
		
		return allFriendship;
	}
	
	public List<User> getAllFriends()
	{
		List<User> allFriends = new ArrayList<User>();
		
		for (Friendship friendship : getAllFriendship())
		{
			if(friendship.getUser1().getId()==this.id)
			{
				allFriends.add(friendship.getUser2());
			}
			if(friendship.getUser2().getId()==this.id)
			{
				allFriends.add(friendship.getUser1());
			}
		}
		return allFriends;
	}
	
	public Friendship getFriendship(User other)
	{
		int idFriend = other.getId();
		for(Friendship f : getAllFriendship())
			if
			(
				(idFriend==f.getUser2().getId() 
				&& 
				this.id==f.getUser1().getId())
				||
				(this.id==f.getUser2().getId()
				&&
				idFriend==f.getUser1().getId())
			)
				return f;
		return null;
				
	}
	
	
	@Override
	public List<String> getErrors() 
	{
		List <String> res = new ArrayList <String>();
		
		if(!hasValue(nickname))
			res.add("Missing value for field nickname");
		if(!hasValue(password))
			res.add("Missing value for field password");
		
		 return res;
	}
}
