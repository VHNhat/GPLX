package team2.api.mobile.gplx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import team2.api.mobile.gplx.models.HistoricalExam;

public interface HistoricalRepository extends MongoRepository<HistoricalExam, String> {
	
}
