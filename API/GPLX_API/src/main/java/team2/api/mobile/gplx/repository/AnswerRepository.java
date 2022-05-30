package team2.api.mobile.gplx.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import team2.api.mobile.gplx.models.Answer;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {

	List<Answer> findByQuestionId(String id);

}
