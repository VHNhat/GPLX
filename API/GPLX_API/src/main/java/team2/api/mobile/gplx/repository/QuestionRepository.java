package team2.api.mobile.gplx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import team2.api.mobile.gplx.models.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
	
}
