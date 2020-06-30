package com.balu.token19.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Sliders extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sliderSeq")
	@SequenceGenerator(name = "sliderSeq", sequenceName = "SLIDER_ID_SEQ", allocationSize = 1)
	private Long sliderId;

	public String sliderName;
	
	@NotNull
	public String sliderPath;
	
	@NotNull
	public String sliderViewType;
	

}
