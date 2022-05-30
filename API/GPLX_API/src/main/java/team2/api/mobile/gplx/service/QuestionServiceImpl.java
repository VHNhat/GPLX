package team2.api.mobile.gplx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team2.api.mobile.gplx.commondata.GenericServiceImpl;
import team2.api.mobile.gplx.models.Question;
import team2.api.mobile.gplx.repository.QuestionRepository;
import team2.api.mobile.gplx.service.interfaces.QuestionService;

@Service
public class QuestionServiceImpl extends GenericServiceImpl<Question, String> implements QuestionService{
	@Autowired
	private QuestionRepository repo;
}
