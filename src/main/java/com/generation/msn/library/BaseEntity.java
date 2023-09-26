package com.generation.msn.library;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	public abstract List<String> getErrors();
	
	public boolean isValid()
	{
		return getErrors().size()==0;
	}
	
	public boolean hasValue(String s)
	{
		return s!=null && !s.isBlank();
	}
	
	
}
