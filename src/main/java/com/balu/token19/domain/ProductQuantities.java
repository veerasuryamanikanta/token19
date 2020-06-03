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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "productquantities")
public class ProductQuantities extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "productquantitiesSeq")
	@SequenceGenerator(name = "productquantitiesSeq", sequenceName = "PRODUCTQUANTITIES_ID_SEQ", allocationSize = 1)
	private Long productquantityId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "quantityId")
	public Quantity quantity;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	public Product product;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productquantities", cascade = CascadeType.MERGE)
	public Set<ProductImages> productimages;

	public String description;

	public String mrpprice;

	public String discount;

	public String sellingprice;

	public boolean isavailable;

}
