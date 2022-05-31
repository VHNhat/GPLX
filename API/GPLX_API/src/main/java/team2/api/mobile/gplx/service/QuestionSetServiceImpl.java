package team2.api.mobile.gplx.service;

<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 401aea24846de59229efd20b967a0148542fda56
import org.springframework.stereotype.Service;

import team2.api.mobile.gplx.commondata.GenericServiceImpl;
import team2.api.mobile.gplx.models.QuestionSet;
<<<<<<< HEAD
=======
import team2.api.mobile.gplx.repository.QuestionSetRepository;
>>>>>>> 401aea24846de59229efd20b967a0148542fda56
import team2.api.mobile.gplx.service.interfaces.QuestionSetService;

@Service
public class QuestionSetServiceImpl extends GenericServiceImpl<QuestionSet, String> implements QuestionSetService {
<<<<<<< HEAD

=======
	@Autowired
	private QuestionSetRepository repo;

	@Override
	public QuestionSet update(QuestionSet set, String id) {
		try {
			QuestionSet updatedSet = repo.findById(id).get();
			updatedSet.setName(set.getName());
			updatedSet.setQuantity(set.getQuantity());
			updatedSet.setRightAns(set.getRightAns());
			updatedSet.setWrongAns(set.getWrongAns());
			updatedSet.setStatus(set.getStatus());
			return repo.save(updatedSet);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
>>>>>>> 401aea24846de59229efd20b967a0148542fda56
}
