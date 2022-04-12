package team2.api.mobile.gplx.service;

import team2.api.mobile.gplx.commondata.GenericService;
import team2.api.mobile.gplx.models.Account;

public interface AccountService extends GenericService<Account, String> {
	
	Account update(Account account, String id);

}
