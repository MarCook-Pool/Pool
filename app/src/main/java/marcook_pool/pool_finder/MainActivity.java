package marcook_pool.pool_finder;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    public static String KEY_SORT_PREF = "sort_pref";
    public static String KEY_FILTER_PREF = "filter_pref";

    private final String TABLE_LOCATIONS = "table_locations";
    private final String SUBMIT_LOCATION = "submit_location";

    private boolean mDoneLogin = false;

    SharedPreferences mPrefs;
    PoolLocationsFragment mPoolLocationsFragment = new PoolLocationsFragment();
    SubmitLocationFragment mSubmitLocationFragment = new SubmitLocationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = getPreferences(Context.MODE_PRIVATE); //TODO: for remembering login

        mDoneLogin = getIntent().getBooleanExtra(LoginActivity.DONE_LOGIN, false);
        if (!mDoneLogin) {
            Log.d(TAG, "" + mDoneLogin);
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            this.startActivity(intent);
        }
        makeTabs();
    }

    private void makeTabs() {
        //TODO: implement swipe views
        ActionBar actionBar = getSupportActionBar();
        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                if (tab.getTag() == TABLE_LOCATIONS) {
                    ft.replace(R.id.activity_main, mPoolLocationsFragment);
                } else if (tab.getTag() == SUBMIT_LOCATION) {
                    ft.replace(R.id.activity_main, mSubmitLocationFragment);
                }
                //any other option is an error, do nothing
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                //ignore this event
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                //ignore this event
            }
        };

        actionBar.addTab(actionBar.newTab().setText(getString(R.string.table_locations_tab))
                .setTag(TABLE_LOCATIONS).setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText(getString(R.string.submit_tables_tab))
                .setTag(SUBMIT_LOCATION).setTabListener(tabListener));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                //TODO: search for pool table locations
                return true;
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.review_app:
                //TODO: send to play store or some review process
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
