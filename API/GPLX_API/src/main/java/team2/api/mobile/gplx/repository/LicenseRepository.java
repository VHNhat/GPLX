package team2.api.mobile.gplx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import team2.api.mobile.gplx.models.License;

@Repository
public interface LicenseRepository extends MongoRepository<License, String> {

<<<<<<< HEAD
	License findByName(String string);
=======
	License findByName(String name);
>>>>>>> 401aea24846de59229efd20b967a0148542fda56

}
