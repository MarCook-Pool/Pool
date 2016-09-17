package marcook_pool.pool_finder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    boolean doneLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doneLogin = getIntent().getBooleanExtra(LoginActivity.DONE_LOGIN, false);

        if (!doneLogin)
        {
            Log.v("Debug", "" + doneLogin);
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            this.startActivity(intent);
        }
    }
}
