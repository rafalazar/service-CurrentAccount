package com.rafalazar.bootcamp.app.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document(collection="current_account")
public class CurrentAccount {
	
	@Id
	private String id;
	private String numAccount;
	private String nameBank;
	private double total;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateAt;
	private String status;
	
	public CurrentAccount() {
		
	}
	
	public CurrentAccount(String numAccount,String nameBank,double total, String status) {
		
		this.numAccount = numAccount;
		this.nameBank = nameBank;
		this.total = total;
		this.status = status;
		
	}
	

}
