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

//	@Override
//	public License save(AddLicenseDto dto) {
//		try {
//			License license = new License();
//			license.setName(dto.getName());
//			license.setDescription(dto.getDescription());
//			if(dto.getStatus() == "ACTIVE") {
//				license.setStatus(Status.ACTIVE);
//			}
//			else if(dto.getStatus() == "EXPIRED") {
//				license.setStatus(Status.EXPIRED);
//			}
//			else if(dto.getStatus() == "BLOCKED") {
//				license.setStatus(Status.BLOCKED);
//			}
//			else if(dto.getStatus() == "INACTIVE") {
//				license.setStatus(Status.INACTIVE);
//			}
//			else license.setStatus(null);
//		} catch(Exception ex) {
//			
//		}
//		return null;
//	}
}
