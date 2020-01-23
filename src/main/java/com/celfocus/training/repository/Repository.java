package com.celfocus.training.repository;

import com.celfocus.training.exception.ApplicationException;

public interface Repository<T> {

	void create(T data);

	int find(T data) throws ApplicationException;

	void delete(int id);

}
