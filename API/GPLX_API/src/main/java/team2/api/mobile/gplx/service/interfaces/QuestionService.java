package team2.api.mobile.gplx.service.interfaces;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 401aea24846de59229efd20b967a0148542fda56
import team2.api.mobile.gplx.commondata.GenericService;
import team2.api.mobile.gplx.models.Question;

public interface QuestionService extends GenericService<Question, String> {
<<<<<<< HEAD
	
=======

	Question update(Question question, String id);

	List<Question> findByQuestionSetId(String id);

>>>>>>> 401aea24846de59229efd20b967a0148542fda56
}
