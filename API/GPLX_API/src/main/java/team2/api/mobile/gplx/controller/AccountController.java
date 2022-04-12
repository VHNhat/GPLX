package team2.api.mobile.gplx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import team2.api.mobile.gplx.models.Account;
import team2.api.mobile.gplx.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@GetMapping("api/account")
	public ResponseEntity<Object> GetAll() {
		List<Account> accounts = service.findAll();
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@PostMapping("api/account/add")
	public ResponseEntity<Object> Post(Account account) {
		Account newAccount = service.save(account);
		if (newAccount == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(newAccount, HttpStatus.OK);
	}

	@PutMapping("api/account/edit/{id}")
	public ResponseEntity<Object> Put(@PathVariable("id") String id, Account account) {
		Account updatedAccount = service.update(account, id);
		if (updatedAccount == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
	}

	@DeleteMapping("api/account/delete/{id}")
	public ResponseEntity<Object> Delete(@PathVariable("id") String id){
		try {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
