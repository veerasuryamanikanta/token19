package com.balu.token19.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class ShopDetails extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shopdetailsSeq")
	@SequenceGenerator(name = "shopdetailsSeq", sequenceName = "SHOPDETAILS_ID_SEQ", allocationSize = 1)
	private Long shopdetailsId;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;

	@Column(nullable = true, updatable = true)
	@NotNull
	private String shopName;

	@Column(nullable = true, unique = true, updatable = true)
	@NotNull
	private String ownerName;
	
	@Column(nullable = true, unique = true, updatable = true)
	@NotNull
	private String pincode;
	
	@Column(nullable = true, unique = true, updatable = true)
	@NotNull
	private String shopAddress;
	
	@Column(nullable = true, unique = true, updatable = true)
	@NotNull
	private String shopLatitude;
	
	@Column(nullable = true, unique = true, updatable = true)
	@NotNull
	private String shopLongitude;

	@Column(nullable = true, updatable = true)
	@NotNull
	public String shopImage;
	
	@Column(columnDefinition = "boolean default false")
	public Boolean ishomeDelivery = false;
	
	@Column(columnDefinition = "boolean default true")
	public Boolean isSelfPickup = true;

}
