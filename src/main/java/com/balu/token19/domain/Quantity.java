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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Quantity extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "quantitySeq")
	@SequenceGenerator(name = "quantitySeq", sequenceName = "QUANTITY_ID_SEQ", allocationSize = 1)
	private Long quantityId;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quantity", cascade = CascadeType.MERGE)
	public Set<ProductQuantities> productquantities;

	@NotNull
	@Column(unique = true)
	public String quantityName;

	@NotNull
	@Column(unique = true)
	public String quantityCode;

}
