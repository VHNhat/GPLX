package team2.api.mobile.gplx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import team2.api.mobile.gplx.models.HistoricalExam;
import team2.api.mobile.gplx.service.HistoricalExamServiceImpl;

@RestController
public class HistoricalExamController {
	@Autowired
	private HistoricalExamServiceImpl historicalExamService;

	@GetMapping("api/histories")
	public ResponseEntity<Object> GetAll() {
		try {
			List<HistoricalExam> list = historicalExamService.findAll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
