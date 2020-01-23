package com.celfocus.training.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemInfo {

	public String name;
	public double value;
}
