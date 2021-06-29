package com.assessment.test.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Builder
public class Warranty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    private long warrantyID;

	@Column(name = "start_date")
    private Date startDate;

	@Column(name = "cust_id")
    private long customerID;
}
