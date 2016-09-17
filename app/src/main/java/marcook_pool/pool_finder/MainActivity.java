package marcook_pool.pool_finder;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button testButton = (Button) findViewById(R.id.test_button);
    TextView testText = (TextView) findViewById(R.id.textView);

    FragmentManager mFragmentManager = getSupportFragmentManager();
    TableLocationsFragment mTableLocationsFragment = new TableLocationsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testText.setText(getString(R.string.test_string));
                mFragmentManager.beginTransaction().replace(R.id.activity_main, mTableLocationsFragment).commit();

            }
        });
    }


}
