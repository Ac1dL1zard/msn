package com.generation.msn.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.msn.library.BaseEntity;

import jakarta.persistence.Entity;
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
	private LocalDate sending_date_time;	
	
	@ManyToOne
	@JoinColumn(name="friendship_id")
	Friendship friendship;
	
	
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
