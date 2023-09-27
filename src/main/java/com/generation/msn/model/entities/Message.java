package com.generation.msn.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.generation.msn.library.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Message extends BaseEntity

{
	
	private String content,sender_nickname;
	private LocalDateTime sending_date_time;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="friendship_id")
	Friendship friendship;
	
	@ManyToOne
	@JoinColumn(name="group_chat_id")
	GroupChat groupchat;
	
	
	@Override
	public List<String> getErrors() 
	{
		List <String> res = new ArrayList <String>();
		
		if(!hasValue(sender_nickname))
			res.add("Missing nickname of the sender");
		if(!hasValue(content))
			res.add("Missing message content");
		
		 return res;
	}

}
