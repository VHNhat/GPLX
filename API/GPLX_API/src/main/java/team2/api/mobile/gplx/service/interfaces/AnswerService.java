package team2.api.mobile.gplx.service.interfaces;

<<<<<<< HEAD
import team2.api.mobile.gplx.commondata.GenericService;
import team2.api.mobile.gplx.models.Answer;

public interface AnswerService extends GenericService<Answer, String>{
=======

import team2.api.mobile.gplx.commondata.GenericService;
import team2.api.mobile.gplx.models.Answer;

public interface AnswerService extends GenericService<Answer, String> {

	Answer update(Answer answer, String id);

	Answer findByQuestionId(String id);
>>>>>>> 401aea24846de59229efd20b967a0148542fda56

}
