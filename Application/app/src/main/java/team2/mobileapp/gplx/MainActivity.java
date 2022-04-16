package team2.mobileapp.gplx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void Lazy ( View view)
    {
        Intent v = new Intent(this,LazyActivity.class);
        startActivity(v);

    }
    public  void Register ( View view)
    {
        Intent v = new Intent(this,RegisterActivity.class);
        startActivity(v);

    }
    public  void SignIn ( View view)
    {
        Intent v = new Intent(this,SignInActivity.class);
        startActivity(v);

    }
    public  void Welcome ( View view)
    {
        Intent v = new Intent(this,WelcomeActivity.class);
        startActivity(v);

    }
    public  void TestAPI ( View view)
    {
        Intent v = new Intent(this,TestAPIActivity.class);
        startActivity(v);

    }
    public  void ResetPassword ( View view)
    {
        Intent v = new Intent(this,ResetPasswordActivity.class);
        startActivity(v);
    }
    public  void ForgotPassword ( View view)
    {
        Intent v = new Intent(this,ForgotPasswordActivity.class);
        startActivity(v);
    }
    //    public  void login_email ( View view)
//    {
//        Intent v = new Intent(this,login_email.class);
//        startActivity(v);
//    }
    public void LoginFacebook ( View view)
    {
        Intent v = new Intent(this, LoginFacebookActivity.class);
        startActivity(v);
    }
    public void LoginSuccess ( View view)
    {
        Intent v = new Intent(this,LoginSuccessActivity.class);
        startActivity(v);
    }
    public void Tutorial ( View view)
    {
        Intent v = new Intent(this,TutorialActivity.class);
        startActivity(v);
    }
    public void SigninFacebook ( View view)
    {
        Intent v = new Intent(this,LoginFacebookActivity.class);
        startActivity(v);
    }
    //    public void sign_email(View view)
//    {
//        Intent v = new Intent(this,login_email.class);
//        startActivity(v);
//    }
    public void A1Activity(View view)
    {
        Intent a1=new Intent(this,A1Activity.class);
        startActivity(a1);
    }
    public void Home(View view)
    {
        Intent home=new Intent(this,HomeActivity.class);
        startActivity(home);
    }
    public void ReviewWrongTest(View view)
    {
        Intent review=new Intent(this,ReviewWrongTestActivity.class);
        startActivity(review);
    }
    public void Signal(View view)
    {
        Intent signal=new Intent(this,SignalActivity.class);
        startActivity(signal);
    }
}