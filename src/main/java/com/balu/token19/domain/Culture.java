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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Culture extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cultureSeq")
	@SequenceGenerator(name = "cultureSeq", sequenceName = "CULTURE_ID_SEQ", allocationSize = 1)
	private Long cultureId;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "culture", cascade = CascadeType.MERGE)
	public Set<Crop> crop;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	private String title;
	
	private String description;

}
