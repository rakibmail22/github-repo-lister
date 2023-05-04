package com.shpapth.githubrepolister.api.validator;

import com.shpapth.githubrepolister.api.exception.InvalidParameterException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class ListRepositoryValidator {

	private static final List<Integer> VALID_LIMITS = List.of(10, 50, 100);

	public void validate(LocalDate createdFrom, int limit) {
		if (Objects.isNull(createdFrom)) {
			throw new InvalidParameterException("createdFrom", "Required");
		}

		if (!VALID_LIMITS.contains(limit)) {
			throw new InvalidParameterException("limit", "Invalid. Valid limits: %s".formatted(VALID_LIMITS));
		}
	}
}