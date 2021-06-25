package com.assessment.test.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private long warrantyID;
    
    private Date startDate;
    
    private long customerID;
}
