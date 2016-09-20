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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Carson on 17/09/2016.
 */

public class SubmitLocationFragment extends Fragment {

    Activity mActivity = getActivity();
    private DatabaseReference mDatabase;

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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setViews();
        setClickListeners();
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
                if (MainActivity.ACCOUNT == null)
                {
                    Toast.makeText(getContext(), getString(R.string.must_log_in), Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    PoolTable curTable = new PoolTable();
                    curTable.ID = 0;
                    curTable.description = mDescription.getText().toString();
                    if (mEstablishment.getText().toString().isEmpty() || mRating.getRating() < 1)
                    {
                        Toast.makeText(getContext(), getString(R.string.invalid_entry), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    curTable.establishment = mEstablishment.getText().toString();
                    curTable.rating = mRating.getRating();
                    curTable.photoURL = "";

                    mDatabase.child("Tables").child(curTable.establishment).setValue(curTable);
                    Toast.makeText(getContext(), getString(R.string.submitted_table), Toast.LENGTH_SHORT).show();
                    mDescription.setText("");
                    mEstablishment.setText("");
                    mRating.setRating(0);
                }
            }
        });
    }
}
