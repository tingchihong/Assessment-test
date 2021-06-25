package com.assessment.test.service;

import com.assessment.test.dto.TodoDto;

public interface ThirdPartyService {
	TodoDto[] getUserList();
	TodoDto getUser(long id);
}
