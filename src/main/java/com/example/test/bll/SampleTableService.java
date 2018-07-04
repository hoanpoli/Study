
/**
 * @author HoanPoly
 *
 */
package com.example.test.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.dal.SampleTableDao;
import com.example.test.model.SampleTable;

@Service(value = "sampletableService")
@Transactional
public class SampleTableService {
	@Autowired
	private SampleTableDao sampletableDao;

	public List<SampleTable> search() {
		List<SampleTable> res = sampletableDao.search();
		return res;
	}
}
