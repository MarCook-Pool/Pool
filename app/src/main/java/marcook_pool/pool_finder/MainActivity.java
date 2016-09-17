package marcook_pool.pool_finder;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FragmentManager mFragmentManager = getSupportFragmentManager();
    TableLocationsFragment mTableLocationsFragment = new TableLocationsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                //search for pool table locations
                return true;
            case R.id.settings:
                //start settings activity
                return true;
            case R.id.review_app:
                //send to play store or some review process
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
