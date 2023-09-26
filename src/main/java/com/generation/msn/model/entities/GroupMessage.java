package com.generation.msn.model.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GroupMessage 
{
	private String content,sender_nickname;
	private LocalDate sending_date_time;	
	
	@ManyToOne
	@JoinColumn(name="group_chat_id")
	GroupChat groupchat = new GroupChat();
}
