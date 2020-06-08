package com.balu.token19.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Services extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "serviceSeq")
	@SequenceGenerator(name = "serviceSeq", sequenceName = "SERVICE_ID_SEQ", allocationSize = 1)
	private Long serviceId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "shopdetailsId")
	public ShopDetails shopdetails;

	@Column(nullable = false, updatable = true)
	@NotNull
	private String serviceName;

	private String serviceDesc;

	private String servicePrice;

	private String serviceImagePath;

}
