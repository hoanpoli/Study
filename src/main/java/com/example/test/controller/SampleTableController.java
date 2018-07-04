package com.example.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.bll.SampleTableService;
import com.example.test.model.SampleTable;

@RestController
@RequestMapping("/sampletable")
public class SampleTableController {
	@Autowired
	private SampleTableService sampletableService;

	@GetMapping("")
	public ResponseEntity<?> getAllAccountOfOwner() {
		List<SampleTable> stp = sampletableService.search();
		return new ResponseEntity<>(stp, HttpStatus.OK);
	}
}