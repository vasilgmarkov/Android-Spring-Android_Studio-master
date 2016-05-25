package com.example.basketball.controller.activities.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.basketball.R;
import com.example.basketball.controller.activities.main.MainActivity;
import com.example.basketball.controller.managers.LoginCallback;
import com.example.basketball.controller.managers.UserCallBack;
import com.example.basketball.controller.managers.UserLoginManager;
import com.example.basketball.model.User;
import com.example.basketball.model.UserToken;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginCallback, UserCallBack {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private String username,userNick,passW,passW2,email;
    private EditText UserName,PassWord,UserNameR,PassWordR,PassWordR2,EmailR;
    private Button makeReg;
    LinearLayout registerForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button registerVis = (Button) findViewById(R.id.regVisib);
        registerVis.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

              registerForm = (LinearLayout) findViewById(R.id.regLay);

                Button mEmailSignInButton = (Button) findViewById(R.id.user_log_in_button);
                UserNameR = (EditText) findViewById(R.id.nickName);
                EmailR = (EditText) findViewById(R.id.email);
                EmailR.addTextChangedListener(new TextWatcher() {
                    public void afterTextChanged(Editable s) {
                        if (EmailR.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && s.length() > 0)
                        {

                        }
                        else
                        {

                            View focusView = null;

                            EmailR.setError(getString(R.string.error_invalid_emailValid));
                            focusView = EmailR;

                        }
                    }
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}
                });
                PassWordR = (EditText) findViewById(R.id.passwordCreate);
                PassWordR2 = (EditText) findViewById(R.id.passwordCreate2);

                makeReg = (Button) findViewById(R.id.reg);

                makeReg.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        attemptRegister();
                    }
                });
                if(registerForm.getVisibility() == View.VISIBLE){
                    registerForm.setVisibility(View.INVISIBLE);
                }else{
                    registerForm.setVisibility(View.VISIBLE);
                }

                registerForm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.user_log_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to log in the account specified by the login form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(username)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            UserLoginManager.getInstance(this.getApplicationContext()).performLogin(username, password, LoginActivity.this);
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    public void onSuccess(UserToken userToken) {
        showProgress(false);

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSuccess(User userInfo) {
        mEmailView.setText(userInfo.getLogin().toString());
        mPasswordView.setText(userInfo.getPassword());
        attemptLogin();
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("LoginActivity->", "performLogin->onFailure ERROR " + t.getMessage());

        // TODO: Gestionar los diversos tipos de errores. Por ejemplo, no se ha podido conectar correctamente.
        showProgress(false);
        mPasswordView.setError(getString(R.string.error_incorrect_username_or_password));
        mPasswordView.requestFocus();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    private void attemptRegister(){

        userNick =UserNameR.getText().toString();
        email = EmailR.getText().toString();
        passW = PassWordR.getText().toString();
        passW2 = PassWordR2.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(passW) && TextUtils.isEmpty(passW2)) {
            PassWordR.setError(getString(R.string.error_invalid_password));
            PassWordR2.setError(getString(R.string.error_invalid_password));
            focusView = PassWordR;
            cancel = true;
        }

        if (!passW.equals(passW2) ) {
            PassWordR.setError(getString(R.string.error_invalid_passwordReg));
            focusView = PassWordR;
            cancel = true;
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(userNick)) {
            UserNameR.setError(getString(R.string.error_field_required));
            focusView = UserNameR;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            EmailR.setError(getString(R.string.error_field_required));
            focusView = EmailR;
            cancel = true;
        }
        if(!isPasswordValid(passW)){
            PassWordR.setError(getString(R.string.error_invalid_passwordMin));
            focusView = PassWordR;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            User user = new User();
            user.setLogin(UserNameR.getText().toString());
            user.setPassword(PassWordR.getText().toString());
            user.setEmail(EmailR.getText().toString());
            user.setLangKey("en");


            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //     showProgress(true);
            // UserLoginManager.getInstance(this.getApplicationContext()).performLogin(username, password, LoginActivity.this);
            UserLoginManager.getInstance(this.getApplicationContext()).newUser(LoginActivity.this, user);
            registerForm.setVisibility(View.INVISIBLE);
        }


    }
}

