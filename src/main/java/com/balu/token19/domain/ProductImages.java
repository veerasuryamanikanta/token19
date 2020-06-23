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
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "productimages")
public class ProductImages extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "productimagesSeq")
	@SequenceGenerator(name = "productimagesSeq", sequenceName = "PRODUCTIMAGES_ID_SEQ", allocationSize = 1)
	private Long productimageId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "productquantityId")
	public ProductQuantities productquantities;

	public String productimagePath;

}
