package marcook_pool.pool_finder;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by Carson on 17/09/2016.
 */

public class SubmitLocationFragment extends Fragment {

    Activity mActivity = getActivity();

    Button mSubmitButton;
    Button mLocationButton;
    Button mPhotoButton;
    EditText mEstablishment;
    EditText mDescription;
    RatingBar mRating;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_locations, container, false);
        setViews();
        setClickListeners();
        return view;
    }

    private void setViews(){
        mSubmitButton = (Button) getView().findViewById(R.id.submit);
        mLocationButton = (Button) getView().findViewById(R.id.location);
        mPhotoButton = (Button) getView().findViewById(R.id.add_photo_button);
        mEstablishment = (EditText) getView().findViewById(R.id.establishment);
        mDescription = (EditText) getView().findViewById(R.id.description);
        mRating = (RatingBar) getView().findViewById(R.id.rating_bar);
    }

    private void setClickListeners(){
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
