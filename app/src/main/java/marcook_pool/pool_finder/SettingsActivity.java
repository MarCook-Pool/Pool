package marcook_pool.pool_finder;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {
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
    }
}
