package marcook_pool.pool_finder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;
    public static String KEY_DONE_LOGIN = "doneLogin";
    private GoogleApiClient mGoogleApiClient;

    Button mCreateAcc;
    Button mLogin;
    Button mSkipLogin;
    EditText mEmail;
    EditText mPassword;
    EditText mConfirmPass;
    TextView mErrorMessage;
    SignInButton mGoogleSignInBut;
    boolean mIsCreating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCreateAcc = (Button) findViewById(R.id.createButton);
        mLogin = (Button) findViewById(R.id.loginButton);
        mSkipLogin = (Button) findViewById(R.id.skipLoginButton);
        mConfirmPass = (EditText) findViewById(R.id.confirmPass);
        mGoogleSignInBut = (SignInButton) findViewById(R.id.sign_in_button);
        mPassword = (EditText) findViewById(R.id.passLogin);
        mEmail = (EditText) findViewById(R.id.emailLogin);
        mErrorMessage = (TextView) findViewById(R.id.errorMessage);

        // Enable google sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

        if (MainActivity.ACCOUNT != null)
            finish(); //TODO: change to start main activity for result, then finish

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsCreating)
                {
                    mErrorMessage.setText("");
                    mErrorMessage.setVisibility(View.GONE);
                    mConfirmPass.setVisibility(View.GONE);
                    mLogin.setText(getString(R.string.login_button));
                    mIsCreating = false;
                }
                else {
                    boolean isGood = true;
                    if (mEmail.getText().toString().isEmpty())
                    {
                        mErrorMessage.setText(getString(R.string.empty_email));
                        isGood = false;
                    }
                    if (mPassword.getText().toString().isEmpty())
                    {
                        mErrorMessage.setText(mErrorMessage.getText().toString() + "\n" +
                                                getString(R.string.empty_password));
                        isGood = false;
                    }
                    if (isGood)
                    {
                        //TODO: LOGIN TO ACCOUNT, will send intent using KEY_DONE_LOGIN key
                        finish();
                    }
                    else
                        mErrorMessage.setVisibility(View.VISIBLE);
                }
            }
        });

        mCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsCreating)
                {
                    mConfirmPass.setVisibility(View.VISIBLE);
                    mLogin.setText(getString(R.string.back_button));
                    mIsCreating = true;
                }
                else
                {
                    boolean isGood = true;
                    if (mEmail.getText().toString().isEmpty())
                    {
                        mErrorMessage.setText(getString(R.string.empty_email));
                        isGood = false;
                    }
                    if (mPassword.getText().toString().length() < 8)
                    {
                        mErrorMessage.setText(mErrorMessage.getText().toString() + "\n" +
                                getString(R.string.short_password));
                        isGood = false;
                    }
                    if (mPassword.getText().toString() != mConfirmPass.getText().toString())
                    {
                        mErrorMessage.setText(mErrorMessage.getText().toString() + "\n" +
                                getString(R.string.no_password_match));
                        isGood = false;
                    }
                    if (isGood)
                    {
                        //TODO: LOGIN TO ACCOUNT, send intent using KEY_DONE_LOGIN
                        finish();
                    }
                    else
                        mErrorMessage.setVisibility(View.VISIBLE);
                }
            }
        });

        mSkipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mGoogleSignInBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mIsCreating)
        {
            mErrorMessage.setText("");
            mErrorMessage.setVisibility(View.GONE);
            mConfirmPass.setVisibility(View.GONE);
            mLogin.setText(getString(R.string.login_button));
            mIsCreating = false;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            if (result.isSuccess())
                finish();
            MainActivity.ACCOUNT = result.getSignInAccount();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
    }
}
