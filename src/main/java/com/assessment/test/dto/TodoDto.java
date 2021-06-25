package com.assessment.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDto {
	private String userId;
	private int id;
	private String title;
	private Boolean completed;
}
