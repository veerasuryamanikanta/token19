package com.balu.token19.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Request extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "requestSeq")
	@SequenceGenerator(name = "requestSeq", sequenceName = "REQUEST_ID_SEQ", allocationSize = 1)
	private Long requestId;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "shopdetailsId")
	public ShopDetails shopdetails;
	

	@NotNull
	private String requestPath;

	@NotNull
	private String requestStatus;
	
	@NotNull
	private String recieveTime;
	
	@NotNull
	private String tokenNumber;


}
