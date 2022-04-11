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

}