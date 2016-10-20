package marcook_pool.pool_finder.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import marcook_pool.pool_finder.R;
import marcook_pool.pool_finder.fragments.ui.RecyclerViewHolder;
import marcook_pool.pool_finder.fragments.ui.ReviewExistingTableFragment;

/**
 * Created by Carson on 18/10/2016.
 */

public class PoolTableReviewFragment extends Fragment {

    public TextView mEstablishment;
    public TextView mDescription;
    public TextView mLocation;
    public RatingBar mRatingBar;
    public Button mLeaveReview;

    ReviewExistingTableFragment mReviewExistingFragment = new ReviewExistingTableFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pool_table_review, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setViews();
    }

    private void setViews() {
        mEstablishment = (TextView) getView().findViewById(R.id.establishment);
        mDescription = (TextView) getView().findViewById(R.id.description);
        mLocation = (TextView) getView().findViewById(R.id.location);
        mRatingBar = (RatingBar) getView().findViewById(R.id.rating_bar);
        /*Drawable stars = mRatingBar.getProgressDrawable();
        stars.setTint(Color.YELLOW); turning given rating bar yellow*/
        mLeaveReview = (Button) getView().findViewById(R.id.leave_review);

        setViewValues();
    }

    private void setViewValues() {
        Bundle bundle = getArguments();
        mEstablishment.setText(bundle.getString(RecyclerViewHolder.KEY_ESTABLISHMENT));
        mDescription.setText(bundle.getString(RecyclerViewHolder.KEY_DESCRIPTION));
        mLocation.setText(bundle.getString(RecyclerViewHolder.KEY_LOCATION));
        mRatingBar.setRating(bundle.getFloat(RecyclerViewHolder.KEY_RATING_BAR, 0));

        mLeaveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewExistingTable();
            }
        });
    }

    private void reviewExistingTable() {
        Bundle bundle = new Bundle();
        bundle.putString(mEstablishment.getText().toString(), RecyclerViewHolder.KEY_ESTABLISHMENT);
        mReviewExistingFragment.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.activity_one_pool_location, mReviewExistingFragment);
        fragmentTransaction.commit();
    }
}
