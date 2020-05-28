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
@Table(name = "shopcategory")
public class ShopCategory extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "shopcategorySeq")
	@SequenceGenerator(name = "shopcategorySeq", sequenceName = "SHOP_CATEGORY_ID_SEQ", allocationSize = 1)
	private Long shopcategoryId;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shopcategory", cascade = CascadeType.MERGE)
	public Set<ShopSubCategory> shopsubcategory;

	@NotNull
	@Column(unique = true)
	public String shopcategoryName;

}
