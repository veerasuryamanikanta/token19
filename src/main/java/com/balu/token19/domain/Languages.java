package com.balu.token19.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Languages extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "languageSeq")
	@SequenceGenerator(name = "languageSeq", sequenceName = "LANGUAGE_ID_SEQ", allocationSize = 1)
	private Long languageId;

	private String languageMotherName;

	private String languageEnglishName;

}
