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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class ProductsAvailablity extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "paSeq")
	@SequenceGenerator(name = "paSeq", sequenceName = "PA_ID_SEQ", allocationSize = 1)
	private Long paId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategoryId")
	public SubCategory subcategory;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "shopdetailsId")
	public ShopDetails shopdetails;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "productquantityId")
	public ProductQuantities productquantities;

	public String description;
	
	public String imagePath;

	public String mrpprice;

	public String discount;

	public String sellingprice;

	public Boolean isavailable;

}
