package team2.api.mobile.gplx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team2.api.mobile.gplx.commondata.GenericServiceImpl;
import team2.api.mobile.gplx.models.Account;
import team2.api.mobile.gplx.models.License;
import team2.api.mobile.gplx.repository.AccountRepository;

@Service
public class AccountServiceImpl extends GenericServiceImpl<Account, String> implements AccountService {

	@Autowired
	private AccountRepository repo;
	
	@Override
	public Account update(Account account, String id) {
		try {
			Account updatedAccount = repo.findById(id).get();
			updatedAccount.setUsername(account.getUsername());
			updatedAccount.setPassword(account.getPassword());
			updatedAccount.setEmail(account.getEmail());
			updatedAccount.setName(account.getName());
			updatedAccount.setAvatar(account.getAvatar());
			updatedAccount.setStatus(account.getStatus());
			return repo.save(updatedAccount);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
