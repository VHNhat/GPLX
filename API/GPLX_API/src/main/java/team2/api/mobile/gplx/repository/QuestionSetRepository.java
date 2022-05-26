package team2.api.mobile.gplx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import team2.api.mobile.gplx.models.QuestionSet;

@Repository
public interface QuestionSetRepository extends MongoRepository<QuestionSet, String> {

	QuestionSet findByName(String name);


}
