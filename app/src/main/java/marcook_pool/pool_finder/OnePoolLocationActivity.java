package marcook_pool.pool_finder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class OnePoolLocationActivity extends AppCompatActivity {

    public TextView mEstablishment;
    public TextView mDescription;
    public TextView mLocation;
    public RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_pool_location);

        setViews();
    }

    private void setViews() {
        mEstablishment = (TextView) findViewById(R.id.establishment);
        mDescription = (TextView) findViewById(R.id.description);
        mLocation = (TextView) findViewById(R.id.location);
        mRatingBar = (RatingBar) findViewById(R.id.rating_bar);

        setViewValues();
    }

    private void setViewValues(){
        Intent intent = getIntent();
        mEstablishment.setText(intent.getStringExtra(RecyclerViewHolder.KEY_ESTABLISHMENT));
        mDescription.setText(intent.getStringExtra(RecyclerViewHolder.KEY_DESCRIPTION));
        mLocation.setText(intent.getStringExtra(RecyclerViewHolder.KEY_LOCATION));
        mRatingBar.setNumStars(intent.getIntExtra(RecyclerViewHolder.KEY_RATING_BAR,0));
    }
}
