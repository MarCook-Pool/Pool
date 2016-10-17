package marcook_pool.pool_finder.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import marcook_pool.pool_finder.R;
import marcook_pool.pool_finder.fragments.ui.RecyclerViewHolder;

public class OnePoolLocationActivity extends AppCompatActivity {

    public TextView mEstablishment;
    public TextView mDescription;
    public TextView mLocation;
    public RatingBar mRatingBar;
    public Button mLeaveReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_pool_location);

        setViews();
        setViewValues();

        mLeaveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send to review existing table location fragment
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
    }

    private void setViewValues() {
        Intent intent = getIntent();
        mEstablishment.setText(intent.getStringExtra(RecyclerViewHolder.KEY_ESTABLISHMENT));
        mDescription.setText(intent.getStringExtra(RecyclerViewHolder.KEY_DESCRIPTION));
        mLocation.setText(intent.getStringExtra(RecyclerViewHolder.KEY_LOCATION));
        mRatingBar.setRating(intent.getFloatExtra(RecyclerViewHolder.KEY_RATING_BAR, 0));
    }
}