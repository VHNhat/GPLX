package team2.api.mobile.gplx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team2.api.mobile.gplx.commondata.GenericServiceImpl;
import team2.api.mobile.gplx.dto.AddLicenseDto;
import team2.api.mobile.gplx.models.License;
import team2.api.mobile.gplx.models.Status;
import team2.api.mobile.gplx.repository.LicenseRepository;

@Service
public class LicenseServiceImpl extends GenericServiceImpl<License, String> implements LicenseService {

	@Autowired
	private LicenseRepository repo;

	@Override
	public License update(License license, String id) {
		try {
			License updatedLicense = repo.findById(id).get();
			updatedLicense.setName(license.getName());
			updatedLicense.setDescription(license.getDescription());
			updatedLicense.setStatus(license.getStatus());
			return repo.save(updatedLicense);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
