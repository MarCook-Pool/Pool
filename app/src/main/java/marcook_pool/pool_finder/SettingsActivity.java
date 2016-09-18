package marcook_pool.pool_finder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class SettingsActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "SettingsActivity";
    private GoogleApiClient mGoogleApiClient;
    Button mSignInOrOut;
    boolean mIsSignedIn;

    //TODO: make shadow for button style
    Button mFilterButton;
    Button mSortButton;
    Button mManagePrivatesButton;

    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mPrefs = getPreferences(Context.MODE_PRIVATE);
        setViews();
        setClickListeners();
    }

    private void setViews() {
        mFilterButton = (Button) findViewById(R.id.filter);
        mSortButton = (Button) findViewById(R.id.sort);
        mManagePrivatesButton = (Button) findViewById(R.id.manage_privates);
    }

    private void setClickListeners() {
        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDialog(R.array.filter_choices, getString(R.string.filter_results), MainActivity.KEY_FILTER_PREF);
            }
        });
        mSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDialog(R.array.sort_choices, getString(R.string.sort_results), MainActivity.KEY_SORT_PREF);
            }
        });
        mManagePrivatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: deal with private management
            }
        });
    }

    private void makeDialog(int array, String title, final String key) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle(title).setItems(array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mPrefs.edit().putInt(key, which).apply();
            }
        });
        builder.create().show();

        mSignInOrOut = (Button) findViewById(R.id.signInButton);

        // Enable google sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mIsSignedIn = MainActivity.ACCOUNT != null;
        if (mIsSignedIn) {
            mSignInOrOut.setText(getText(R.string.sign_out));
        }

        mSignInOrOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsSignedIn) {
                    signOut();
                    mIsSignedIn = false;
                    MainActivity.ACCOUNT = null;
                    mSignInOrOut.setText(getText(R.string.sign_in));
                } else {
                    Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                    }
                });
    }
}
