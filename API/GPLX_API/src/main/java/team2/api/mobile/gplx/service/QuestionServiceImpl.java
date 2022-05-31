package team2.api.mobile.gplx.service;

<<<<<<< HEAD
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 401aea24846de59229efd20b967a0148542fda56
import org.springframework.stereotype.Service;

import team2.api.mobile.gplx.commondata.GenericServiceImpl;
import team2.api.mobile.gplx.models.Question;
<<<<<<< HEAD
=======
import team2.api.mobile.gplx.repository.QuestionRepository;
>>>>>>> 401aea24846de59229efd20b967a0148542fda56
import team2.api.mobile.gplx.service.interfaces.QuestionService;

@Service
public class QuestionServiceImpl extends GenericServiceImpl<Question, String> implements QuestionService{
<<<<<<< HEAD

=======
	@Autowired
	private QuestionRepository repo;

	@Override
	public Question update(Question question, String id) {
		try {
			Question updatedQuestion = repo.findById(id).get();
			updatedQuestion.setIndex(question.getIndex());
			updatedQuestion.setQuery(question.getQuery());
			updatedQuestion.setTop50(question.isTop50());
			updatedQuestion.setPhoto(question.getPhoto());
			updatedQuestion.setLicenseId(question.getLicenseId());
			updatedQuestion.setQuestionSetId(question.getQuestionSetId());
			updatedQuestion.setQuestionTypeId(question.getQuestionTypeId());
			return repo.save(updatedQuestion);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public List<Question> findByQuestionSetId(String id) {
		return repo.findByQuestionSetId(id);
	}
>>>>>>> 401aea24846de59229efd20b967a0148542fda56
}
