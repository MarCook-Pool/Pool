package marcook_pool.pool_finder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    public static String DONE_LOGIN = "mDoneLogin";

    Button mCreateAcc;
    Button mLogin;
    Button mSkipLogin;
    EditText mConfirmPass;
    boolean mIsCreating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCreateAcc = (Button) findViewById(R.id.createButton);
        mLogin = (Button) findViewById(R.id.loginButton);
        mSkipLogin = (Button) findViewById(R.id.skipLoginButton);
        mConfirmPass = (EditText) findViewById(R.id.confirmPass);

        mCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsCreating)
                {
                    mConfirmPass.setVisibility(View.VISIBLE);
                    mLogin.setVisibility(View.GONE);
                    mIsCreating = true;
                }
                else
                {
                    //TODO: CREATE ACCOUNT
                    finish();
                }
            }
        });

        mSkipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mIsCreating)
        {
            mConfirmPass.setVisibility(View.GONE);
            mLogin.setVisibility(View.VISIBLE);
            mIsCreating = false;
        }
    }
}
