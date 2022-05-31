package team2.api.mobile.gplx.repository;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 401aea24846de59229efd20b967a0148542fda56
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import team2.api.mobile.gplx.models.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
<<<<<<< HEAD
	
=======

	List<Question> findByQuestionSetId(String id);

>>>>>>> 401aea24846de59229efd20b967a0148542fda56
}
