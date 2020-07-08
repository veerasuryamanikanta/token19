package com.balu.token19.domain;

import java.util.Set;

import javax.persistence.CascadeType;
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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class VendorProduct extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "vendorproductSeq")
	@SequenceGenerator(name = "vendorproductSeq", sequenceName = "VENDOR_PRODUCT_ID_SEQ", allocationSize = 1)
	private Long vendorproductId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategoryId")
	public SubCategory subcategory;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	public Product product;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "shopdetailsId")
	public ShopDetails shopdetails;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendorproduct", cascade = CascadeType.MERGE)
	public Set<VendorProductQuantity> vendorproductquantity;
	
	public String productName;

}
