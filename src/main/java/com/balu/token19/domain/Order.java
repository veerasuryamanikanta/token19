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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Order extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "orderSeq")
	@SequenceGenerator(name = "orderSeq", sequenceName = "ORDER_ID_SEQ", allocationSize = 1)
	private Long id;

	@Column(unique=true)	
	public String orderId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.MERGE)
	public Set<OrderItems> orderitems;


}
