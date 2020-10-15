package com.nedupillil337.restservices.Entities;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "orders")
public class Order extends ResourceSupport {
	
	@Id
	@GeneratedValue
	@JsonView(Views.Internal.class)
	
	private Long orderid;
	@JsonView(Views.Internal.class)
	
	private String orderdescription;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public String getOrderdescription() {
		return orderdescription;
	}

	public void setOrderdescription(String orderdescription) {
		this.orderdescription = orderdescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}