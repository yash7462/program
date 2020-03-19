package com.services.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "serviceitem")
@EntityListeners(AuditingEntityListener.class)
public class Services {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long serviceid;

	@Column(name = "servicename", nullable = false)
	private String servicename;

	@Column(name = "servicedescription", nullable = false)
	private String servicedescription;

	@Column(name = "serviceduration", nullable = false)
	private String serviceduration;

	@Column(name = "servicecost", nullable = false)
	private int servicecost;

	@Column(name = "comments", nullable = false)
	private String comments;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "categoryid")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;

	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getServiceid() {
		return serviceid;
	}

	public void setServiceid(Long serviceid) {
		this.serviceid = serviceid;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getServicedescription() {
		return servicedescription;
	}

	public void setServicedescription(String servicedescription) {
		this.servicedescription = servicedescription;
	}

	public String getServiceduration() {
		return serviceduration;
	}

	public void setServiceduration(String serviceduration) {
		this.serviceduration = serviceduration;
	}

	public int getServicecost() {
		return servicecost;
	}

	public void setServicecost(int servicecost) {
		this.servicecost = servicecost;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Services [serviceid=" + serviceid + ", servicename=" + servicename + ", servicedescription="
				+ servicedescription + ", serviceduration=" + serviceduration + ", servicecost=" + servicecost
				+ ", comments=" + comments + ", category=" + category + "]";
	}

	
}