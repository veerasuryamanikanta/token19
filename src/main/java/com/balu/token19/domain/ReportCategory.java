package com.balu.token19.domain;

import javax.persistence.Column;
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
@Entity(name = "reportcategory")
public class ReportCategory extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "reportcategorySeq")
	@SequenceGenerator(name = "reportcategorySeq", sequenceName = "REPORT_CATG_ID_SEQ", allocationSize = 1)
	private Long reportCategoryId;

	@Column(nullable = false, updatable = true)
	@NotNull
	private String reportCategoryName;

	@Column(nullable = false, unique = true, updatable = true)
	@NotNull
	private String reportCategoryCode;

}
