package team2.mobileapp.gplx.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.AccountCallbackListener;
import team2.mobileapp.gplx.Retrofit.controllers.AccountController;
import team2.mobileapp.gplx.Retrofit.models.Account;

public class EditProfileActivity extends AppCompatActivity implements AccountCallbackListener {

    private EditText etFullName;
    private EditText etUsername;
    private EditText etEmail;
    private Button btnSave;

    private Account accountView;
    private AccountController accountController;

    private boolean isUpdated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);

        setContentView(R.layout.activity_edit_profile);

        InitialVariables();
        try {
            accountController.startFetching("629c1f72fd4add256bdb9997");
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        accountView.setFirstName(etFullName.getText().toString());
                        accountView.setUsername(etUsername.getText().toString());
                        accountController.updateAccount(accountView.getId(), accountView);
                    } catch (Exception e) {
                        Log.d("Error:", e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    private void InitialVariables() {
        accountController = new AccountController(this);
        etFullName = (EditText) findViewById(R.id.et_fullname);
        etUsername = (EditText) findViewById(R.id.et_username);
        etEmail = (EditText) findViewById(R.id.et_email_profile);
        btnSave = (Button) findViewById(R.id.btn_save);
    }

    @Override
    public void onFetchAccountProgress(Account account) {
        if (account != null) {
            this.accountView = account;
            etFullName.setText(account.getLastName() + " " + account.getFirstName());
            etUsername.setText(account.getUsername());
            etEmail.setText(account.getEmail());
        }
    }

    @Override
    public void onFetchComplete(String message) {
        Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}