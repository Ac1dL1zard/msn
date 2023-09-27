package com.generation.msn.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.generation.msn.library.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class GroupChat extends BaseEntity
{
	@ManyToMany(mappedBy="groups", fetch = FetchType.EAGER)
	List<User> groupusers = new ArrayList<User>();
	
	@OneToMany(mappedBy = "groupchat", fetch = FetchType.EAGER)
	List<Message> groupmessages =new ArrayList<Message>();
	
	@Override
	public List<String> getErrors() {
		// TODO Auto-generated method stub
		return null;
	}

}
