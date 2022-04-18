package team2.mobileapp.gplx;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import team2.mobileapp.gplx.models.Account;
import team2.mobileapp.gplx.models.dto.RegisterResponse;
import team2.mobileapp.gplx.service.AuthenService;

public class RegisterActivity extends AppCompatActivity {

    EditText et_first_name, et_last_name, et_email, et_username, et_password, et_confirm_password;
    Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_first_name = findViewById(R.id.et_last_name);
        et_last_name = findViewById(R.id.et_first_name);
        et_email = findViewById(R.id.et_email);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_confirm_password = findViewById(R.id.et_confirm_password);

        btn_register = findViewById(R.id.btn_register);

        Intent login = new Intent(this,SignInActivity.class);

        final AuthenService authenService = new AuthenService(this);

        Register(authenService, login);
    }

    private void Register(AuthenService authenService, Intent login) {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_first_name.getText().toString().isEmpty())
                    Toast.makeText(RegisterActivity.this,"Please enter your first name", Toast.LENGTH_LONG).show();
                else if(et_last_name.getText().toString().isEmpty())
                    Toast.makeText(RegisterActivity.this,"Please enter your last name", Toast.LENGTH_LONG).show();
                else if(et_email.getText().toString().isEmpty())
                    Toast.makeText(RegisterActivity.this,"Please enter your email", Toast.LENGTH_LONG).show();
                else if(et_username.getText().toString().isEmpty())
                    Toast.makeText(RegisterActivity.this,"Please enter your username", Toast.LENGTH_LONG).show();
                else if(et_password.getText().toString().isEmpty())
                    Toast.makeText(RegisterActivity.this,"Please enter your password", Toast.LENGTH_LONG).show();
                else if(et_confirm_password.getText().toString().isEmpty())
                    Toast.makeText(RegisterActivity.this,"Please enter your confirm password", Toast.LENGTH_LONG).show();
                else if(!et_password.getText().toString().equals(et_confirm_password.getText().toString()))
                    Toast.makeText(RegisterActivity.this,"Please check your confirm password again!", Toast.LENGTH_LONG).show();
                else{
                    Account account = new Account();
                    account.setFirstName(et_first_name.getText().toString());
                    account.setLastName(et_last_name.getText().toString());
                    account.setEmail(et_email.getText().toString());
                    account.setUsername(et_username.getText().toString());
                    account.setPassword(et_password.getText().toString());
                    authenService.Register(account, new AuthenService.SignupCallBack() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(RegisterActivity.this, "Something wrong!!!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(RegisterResponse registerResponse) {
                            Log.i("Register response",registerResponse.toString());
                            if(registerResponse.getEmail().equals("Email") && registerResponse.getUsername().equals("Username"))
                                Toast.makeText(RegisterActivity.this, "This email and username has already been taken", Toast.LENGTH_LONG).show();
                            else if(registerResponse.getEmail().equals("Email"))
                                Toast.makeText(RegisterActivity.this, "This email has already been taken", Toast.LENGTH_LONG).show();
                            else if(registerResponse.getUsername().equals("Username"))
                                Toast.makeText(RegisterActivity.this, "This username has already been taken", Toast.LENGTH_LONG).show();
                            else {
                                Toast.makeText(RegisterActivity.this, "Congrats!!!", Toast.LENGTH_LONG).show();
                                startActivity(login);
                            }
                        }
                    });
                }
            }
        });
    }
}