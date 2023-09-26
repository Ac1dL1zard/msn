package com.generation.msn.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.msn.library.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Friendship extends BaseEntity
{

	
	private LocalDate start_date_time;	
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name="user1_id")
	@ToString.Exclude
	private User user1;
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name="user2_id")
	@ToString.Exclude
	private User user2;
	
	@OneToMany(mappedBy="friendship")
	private List<Message> massages;
	
	
	@Override
	public List<String> getErrors() 
	{
		List <String> res = new ArrayList <String>();
		
		
		 return res;
	}
	

}
