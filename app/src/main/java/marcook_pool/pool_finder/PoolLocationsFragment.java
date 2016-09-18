package marcook_pool.pool_finder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pools;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Carson on 17/09/2016.
 */

public class PoolLocationsFragment extends Fragment {
    //TODO: use sharedprefs to get how to sort tables
    private DatabaseReference mDatabase;
    private static final String TAG = "PoolLocationsFragment";

    TextView mOutput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Query tableQuery = mDatabase.child("Tables").orderByChild("establishment");

        return inflater.inflate(R.layout.fragment_pool_locations, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mOutput = (TextView) getView().findViewById(R.id.textView);

        mDatabase.child("Tables").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String output = "";
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PoolTable curTable = snapshot.getValue(PoolTable.class);
                    if (curTable == null) {
                        // User is null, error out
                        Log.e(TAG, "Pool table is unexpectedly null");
                    } else {
                        //TODO: Add card;
                        output += curTable.establishment + ": " + curTable.description + "\n";
                        mOutput.setText(output);
                        Log.d(TAG, "Pool Table: " + curTable.establishment);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "PoolTableLocations: ", databaseError.toException());
            }
        });
    }
}
