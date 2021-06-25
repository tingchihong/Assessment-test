package com.assessment.test.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@Setter
@Getter
public class WarrantyDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long warrantyID;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date startDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long customerID;
}
