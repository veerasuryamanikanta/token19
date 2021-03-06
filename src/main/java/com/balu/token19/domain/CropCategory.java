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
@Entity(name = "cropcategory")
public class CropCategory extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cropcategorySeq")
	@SequenceGenerator(name = "cropcategorySeq", sequenceName = "CROP_CATG_ID_SEQ", allocationSize = 1)
	private Long cropCategoryId;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cropcategory", cascade = CascadeType.MERGE)
	public Set<Crop> crop;

	@Column(nullable = false, updatable = true)
	@NotNull
	private String cropCategoryName;

	@Column(nullable = false, unique = true, updatable = true)
	@NotNull
	private String cropCategoryCode;

}
