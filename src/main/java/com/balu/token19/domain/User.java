package com.balu.token19.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class User extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "userSeq")
	@SequenceGenerator(name = "userSeq", sequenceName = "USER_ID_SEQ", allocationSize = 1)
	private Long userId;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	public Role role;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
	public Set<ShopDetails> shopdetails;

	@NotNull
	private String userName;

	@Column(nullable = false, unique = true, updatable = true)
	@NotNull
	private String userNumber;
	
	@NotNull
	private String userEmail;
	
	@NotNull
	private String userAddress;

	@NotNull
	public String userImage;

}
