package com.rafalazar.bootcamp.app.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PersonalDto {
	
	private String id;
	private String name;
	private String lastName;
	private String dni;
	private String address;
	private char sex;
	private Date birthDay;
	private Date joinAt;

}
