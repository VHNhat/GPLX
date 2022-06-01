package team2.api.mobile.gplx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team2.api.mobile.gplx.dto.DtoQuestionDetail;
import team2.api.mobile.gplx.models.Answer;
import team2.api.mobile.gplx.models.Question;
import team2.api.mobile.gplx.models.QuestionSet;
import team2.api.mobile.gplx.service.interfaces.AnswerService;
import team2.api.mobile.gplx.service.interfaces.QuestionService;

@RestController
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;

	@GetMapping("api/question")
	public ResponseEntity<Object> GetAll() {
		List<Question> questions = questionService.findAll();
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@PostMapping("api/question/add")
	public ResponseEntity<Object> Post(@RequestBody Question question) {
		Question newQuestion = questionService.save(question);
		if (newQuestion == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(newQuestion, HttpStatus.OK);
	}

	@PutMapping("api/question/edit/{id}")
	public ResponseEntity<Object> Put(@PathVariable("id") String id, @RequestBody Question question) {
		Question updatedQuestion = questionService.update(question, id);
		if (updatedQuestion == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
	}

	@DeleteMapping("api/question/delete/{id}")
	public ResponseEntity<Object> Delete(@PathVariable("id") String id) {
		try {
			questionService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("api/question/{license}")
	public ResponseEntity<Object> GetQuestionByLicense(@PathVariable("license") String license) {
		List<Question> questions = questionService.findQuestionByLicense(license);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
	
	@GetMapping("api/question/details/{id}")
	public ResponseEntity<Object> GetQuestionDetails(@PathVariable("id") String id) {
		try {
			Question question = questionService.findById(id).get();
			Answer answer = answerService.findByQuestionId(id);
			DtoQuestionDetail questionDetail = new DtoQuestionDetail(question, answer);
			return new ResponseEntity<>(questionDetail, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
