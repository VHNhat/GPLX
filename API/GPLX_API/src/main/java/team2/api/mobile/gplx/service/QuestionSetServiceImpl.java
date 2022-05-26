package team2.api.mobile.gplx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team2.api.mobile.gplx.commondata.GenericServiceImpl;
import team2.api.mobile.gplx.models.QuestionSet;
import team2.api.mobile.gplx.repository.QuestionSetRepository;
import team2.api.mobile.gplx.service.interfaces.QuestionSetService;

@Service
public class QuestionSetServiceImpl extends GenericServiceImpl<QuestionSet, String> implements QuestionSetService {
	@Autowired
	private QuestionSetRepository repo;
}
