package team2.api.mobile.gplx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import team2.api.mobile.gplx.service.interfaces.QuestionService;

@RestController
public class QuestionController {
	@Autowired
	private QuestionService service;
}
