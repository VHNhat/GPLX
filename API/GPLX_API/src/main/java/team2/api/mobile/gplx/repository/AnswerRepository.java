package team2.api.mobile.gplx.repository;

<<<<<<< HEAD
=======

>>>>>>> 401aea24846de59229efd20b967a0148542fda56
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import team2.api.mobile.gplx.models.Answer;

@Repository
<<<<<<< HEAD
public interface AnswerRepository extends MongoRepository<Answer,String>{
=======
public interface AnswerRepository extends MongoRepository<Answer, String> {

	Answer findByQuestionId(String id);
>>>>>>> 401aea24846de59229efd20b967a0148542fda56

}
