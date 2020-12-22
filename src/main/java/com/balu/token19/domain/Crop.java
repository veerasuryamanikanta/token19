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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Crop extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cropSeq")
	@SequenceGenerator(name = "cropSeq", sequenceName = "CROP_ID_SEQ", allocationSize = 1)
	private Long cropId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "cultureId")
	public Culture culture;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "cropCategoryId")
	public CropCategory cropcategory;

	private String cropname;

	private String description;

	private String imagepath;


}
