package com.balu.token19.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategoryId")
	public ShopSubCategory shopsubcategory;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopdetails", cascade = CascadeType.MERGE)
	public Set<Request> request;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopdetails", cascade = CascadeType.MERGE)
	public Set<Services> services;

	@NotNull
	private String shopName;

	@NotNull
	private String ownerName;
	
	@NotNull
	private String pincode;
	
	@NotNull
	private String shopAddress;
	
	@NotNull
	private String shopLatitude;
	
	@NotNull
	private String shopLongitude;

	@NotNull
	public String shopImage;
	
	@Column(columnDefinition = "boolean default false")
	public Boolean ishomeDelivery = false;
	
	@Column(columnDefinition = "boolean default true")
	public Boolean isSelfPickup = true;

}
