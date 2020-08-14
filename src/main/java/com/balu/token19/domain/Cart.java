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

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Cart extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cartSeq")
	@SequenceGenerator(name = "cartSeq", sequenceName = "CART_ID_SEQ", allocationSize = 1)
	private Long cartId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "productquantityId")
	public ProductQuantities productquantities;

	@NotNull
	public String itemQuantity;

	@NotNull
	public String productMrp;

	@NotNull
	public String sellingPrice;

}
