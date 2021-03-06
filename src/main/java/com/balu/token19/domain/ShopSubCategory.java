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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "shopsubcategory")
public class ShopSubCategory extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shopsubcategorySeq")
	@SequenceGenerator(name = "shopsubcategorySeq", sequenceName = "SHOP_SUBCATEGORY_ID_SEQ", allocationSize = 1)
	private Long shopsubcategoryId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "shopcategoryId")
	public ShopCategory shopcategory;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopsubcategory", cascade = CascadeType.MERGE)
	public Set<ShopDetails> shopdetails;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopsubcategory", cascade = CascadeType.MERGE)
	public Set<SubCategory> subcategory;

	@NotNull
	public String shopsubcategoryName;
	
	@NotNull
	public String shopsubcategoryImage;

}
