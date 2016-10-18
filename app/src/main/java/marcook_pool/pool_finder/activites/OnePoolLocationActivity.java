package marcook_pool.pool_finder.activites;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import marcook_pool.pool_finder.R;
import marcook_pool.pool_finder.fragments.PoolTableReviewFragment;
import marcook_pool.pool_finder.fragments.ui.RecyclerViewHolder;
import marcook_pool.pool_finder.fragments.ui.ReviewExistingTableFragment;

public class OnePoolLocationActivity extends AppCompatActivity {

    PoolTableReviewFragment mPoolTableReviewFragment = new PoolTableReviewFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_pool_location);

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString(intent.getStringExtra(RecyclerViewHolder.KEY_ESTABLISHMENT), RecyclerViewHolder.KEY_ESTABLISHMENT);
        bundle.putString(intent.getStringExtra(RecyclerViewHolder.KEY_DESCRIPTION), RecyclerViewHolder.KEY_DESCRIPTION);
        bundle.putString(intent.getStringExtra(RecyclerViewHolder.KEY_LOCATION), RecyclerViewHolder.KEY_LOCATION);
        bundle.putFloat(RecyclerViewHolder.KEY_RATING_BAR, intent.getFloatExtra(RecyclerViewHolder.KEY_RATING_BAR, 0));
        mPoolTableReviewFragment.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.activity_one_pool_location, mPoolTableReviewFragment);
        fragmentTransaction.commit();
    }
}
