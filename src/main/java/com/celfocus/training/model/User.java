package com.celfocus.training.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

	public String name;
	public Date birthdayDate;
}
