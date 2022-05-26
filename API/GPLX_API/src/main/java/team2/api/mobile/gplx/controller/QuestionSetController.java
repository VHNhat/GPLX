package team2.api.mobile.gplx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import team2.api.mobile.gplx.service.interfaces.QuestionSetService;

@RestController
public class QuestionSetController {
	@Autowired
	private QuestionSetService service;
}
