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
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Product extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "productSeq")
	@SequenceGenerator(name = "productSeq", sequenceName = "PRODUCT_ID_SEQ", allocationSize = 1)
	private Long productId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategoryId")
	public SubCategory subcategory;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "productcategoryId")
	public ProductCategory productcategory;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.MERGE)
	public Set<ProductQuantities> productquantities;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.MERGE)
	public Set<VendorProduct> vendorproduct;

	@NotNull
	private String productName;

	@NotNull
	private String productImagePath;

	@NotNull
	private String shortDescription;

	private String longDescription;

}
