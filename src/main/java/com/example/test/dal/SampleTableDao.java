package com.example.test.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.test.model.SampleTable;

public interface SampleTableDao extends CrudRepository<SampleTable, Integer> {
	@Query("FROM SampleTable")
	public List<SampleTable> search();
}
