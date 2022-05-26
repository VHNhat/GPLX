package team2.api.mobile.gplx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team2.api.mobile.gplx.commondata.GenericServiceImpl;
import team2.api.mobile.gplx.models.Answer;
import team2.api.mobile.gplx.repository.AnswerRepository;
import team2.api.mobile.gplx.service.interfaces.AnswerService;

@Service
public class AnswerServiceImpl extends GenericServiceImpl<Answer, String> implements AnswerService {
	@Autowired
	private AnswerRepository repo;
}
