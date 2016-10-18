package marcook_pool.pool_finder.activites;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import marcook_pool.pool_finder.R;
import marcook_pool.pool_finder.fragments.ui.RecyclerViewHolder;
import marcook_pool.pool_finder.fragments.ui.ReviewExistingTableFragment;

public class OnePoolLocationActivity extends AppCompatActivity {
    public static final String KEY_ESTABLISHMENT = "key_establishment";


    public TextView mEstablishment;
    public TextView mDescription;
    public TextView mLocation;
    public RatingBar mRatingBar;
    public Button mLeaveReview;

    ReviewExistingTableFragment mReviewExistingFragment = new ReviewExistingTableFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_pool_location);

        setViews();

        mLeaveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString((String) mEstablishment.getText(), KEY_ESTABLISHMENT);
                mReviewExistingFragment.setArguments(bundle);

                disappearViews();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.activity_one_pool_location, mReviewExistingFragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void setViews() {
        mEstablishment = (TextView) findViewById(R.id.establishment);
        mDescription = (TextView) findViewById(R.id.description);
        mLocation = (TextView) findViewById(R.id.location);
        mRatingBar = (RatingBar) findViewById(R.id.rating_bar);
        /*Drawable stars = mRatingBar.getProgressDrawable();
        stars.setTint(Color.YELLOW); turning given rating bar yellow*/
        mLeaveReview = (Button) findViewById(R.id.leave_review);

        setViewValues();
    }

    private void setViewValues() {
        Intent intent = getIntent();
        mEstablishment.setText(intent.getStringExtra(RecyclerViewHolder.KEY_ESTABLISHMENT));
        mDescription.setText(intent.getStringExtra(RecyclerViewHolder.KEY_DESCRIPTION));
        mLocation.setText(intent.getStringExtra(RecyclerViewHolder.KEY_LOCATION));
        mRatingBar.setRating(intent.getFloatExtra(RecyclerViewHolder.KEY_RATING_BAR, 0));
    }

    private void disappearViews(){
        mEstablishment.setVisibility(View.GONE);
        mDescription.setVisibility(View.GONE);
        mRatingBar.setVisibility(View.GONE);
        mLocation.setVisibility(View.GONE);
        mLeaveReview.setVisibility(View.GONE);
    }
}
