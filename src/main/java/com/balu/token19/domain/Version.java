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
public class Version extends Root{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "versionSeq")
	@SequenceGenerator(name = "versionSeq", sequenceName = "VERSION_ID_SEQ", allocationSize = 1)
	private Long versionId;
	
	private String versionName;
	
	@NotNull
	public int versionCode;

	public String sourceType;

	@NotNull
	public String updatePath;

	public String updateMessage;

	@NotNull
	public Boolean isMandatory;
	
}
