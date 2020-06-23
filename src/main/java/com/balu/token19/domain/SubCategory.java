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
@Table(name = "subcategory")
public class SubCategory extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "subcategorySeq")
	@SequenceGenerator(name = "subcategorySeq", sequenceName = "SUBCATEGORY_ID_SEQ", allocationSize = 1)
	private Long subcategoryId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	public Category category;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subcategory", cascade = CascadeType.MERGE)
	public Set<Product> product;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subcategory", cascade = CascadeType.MERGE)
	public Set<ProductsAvailablity> productsavailablity;

	@NotNull
	public String subcategoryName;
	
	@NotNull
	public String subcategoryImage;

}
