package com.rafalazar.bootcamp.app.dto;

import java.util.Date;



import lombok.Data;

@Data
public class EnterpriseDto {
	
	private String id;
	private String ruc;
	private String socialName;
	private String address;
	private Date joinAt;
	private Date updateAt;
	private AccountDto account;

}
