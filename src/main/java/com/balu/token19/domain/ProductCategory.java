package com.balu.token19.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "productcategory")
public class ProductCategory extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "productcategorySeq")
	@SequenceGenerator(name = "productcategorySeq", sequenceName = "ITEM_CATEGORY_ID_SEQ", allocationSize = 1)
	private Long productcategoryId;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productcategory", cascade = CascadeType.MERGE)
	public Set<Product> product;

	@NotNull
	@Column(unique = true)
	public String productcategoryName;

	public String productcategoryImage;

}
