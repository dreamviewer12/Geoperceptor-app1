package abcd.com.databaseauto;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Nitisha Agarwal on 26-11-2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /*  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
              = new BottomNavigationView.OnNavigationItemSelectedListener() {

          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              switch (item.getItemId()) {
                  case R.id.navigation_home:

                      return true;
                  case R.id.navigation_dashboard:
                      return true;

              }
              return false;
          }

      };*/
    private Button btnSignUp;
    private Button btnSign;
    private EditText etpass;
    private EditText etemail;
    private ProgressBar pbStatus;
    private SignInButton signInButton;

    PackageManager pm;
    ComponentName componentName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        etemail = (EditText)findViewById(R.id.email);
        etpass = (EditText)findViewById(R.id.password);
        btnSign = (Button)findViewById(R.id.email_sign_in_button);

        btnSignUp = (Button)findViewById(R.id.signUpTextView);
        pbStatus = (ProgressBar)findViewById(R.id.login_progress);
        btnSign.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);



        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent homeIntent = new Intent(LoginActivity.this, ChooseActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                } else {
                    // User is signed out
                }

                // ...
            }
        };

       /* *//*signInButton = (SignInButton) findViewById(R.id.google);*//*
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);*/

        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    @Override
    public void onClick(View v) {
        show();
        String email=etemail.getText().toString();
        String pass=etpass.getText().toString();
        if(email.isEmpty())
        {
            etemail.setError("You can not leave this empty");
            return;
        }
        if(pass.isEmpty()&&pass.length()<8)
        {
            etpass.setError("cannot be empty,min 8 characters");
        }
        switch(v.getId())
        {
            case R.id.email_sign_in_button:

                tryLogin(email,pass);
                break;
            case R.id.signUpTextView:

                tryLogin(email,pass);
                trySignUp(email,pass);
                break;
           /* case R.id.google:
                google1();
                break;*/
        }


    }

 /*   private void google1()
    {
        Intent i= new Intent(this,GoogleSignIn.class);
        startActivity(i);
    }
*/

    private void trySignUp(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hide();
                        //Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Log.d("codekamp","Successful signup");
                            pm  = LoginActivity.this.getPackageManager();
                            componentName = new ComponentName(LoginActivity.this, SMSBReceiver.class);
                            pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                    PackageManager.DONT_KILL_APP);
                            //firebase extra info for the user.
                        }

                        // ...
                    }
                });
    }
    public void show()
    {
        pbStatus.setVisibility(View.VISIBLE);
    }
    public void hide()
    {
        pbStatus.setVisibility(View.INVISIBLE);
    }

    private void tryLogin(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hide();
                        // Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            // Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            pm  = LoginActivity.this.getPackageManager();
                            componentName = new ComponentName(LoginActivity.this, SMSBReceiver.class);
                            pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                    PackageManager.DONT_KILL_APP);
                            Log.d("codekamp","Successful login");
                        }

                        // ...
                    }
                });
    }

}

