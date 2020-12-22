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
public class Technician extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "technicianSeq")
	@SequenceGenerator(name = "technicianSeq", sequenceName = "TECHIE_ID_SEQ", allocationSize = 1)
	private Long technicianId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;

	private String title;

	private String description;

	private String blogurl;

	private String experience;

}
