package team2.api.mobile.gplx.service;

<<<<<<< HEAD
=======

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 401aea24846de59229efd20b967a0148542fda56
import org.springframework.stereotype.Service;

import team2.api.mobile.gplx.commondata.GenericServiceImpl;
import team2.api.mobile.gplx.models.Answer;
<<<<<<< HEAD
import team2.api.mobile.gplx.service.interfaces.AnswerService;

@Service
public class AnswerServiceImpl extends GenericServiceImpl<Answer, String> implements AnswerService{

=======
import team2.api.mobile.gplx.repository.AnswerRepository;
import team2.api.mobile.gplx.service.interfaces.AnswerService;

@Service
public class AnswerServiceImpl extends GenericServiceImpl<Answer, String> implements AnswerService {
	@Autowired
	private AnswerRepository repo;

	@Override
	public Answer update(Answer answer, String id) {
		try {
			Answer updatedAnswer = repo.findById(id).get();
			updatedAnswer.setAnswerList(answer.getAnswerList());
			updatedAnswer.setQuestionId(answer.getQuestionId());
			updatedAnswer.setResult(answer.getResult());
			return repo.save(updatedAnswer);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public Answer findByQuestionId(String id) {
		return repo.findByQuestionId(id);
	}
>>>>>>> 401aea24846de59229efd20b967a0148542fda56
}
