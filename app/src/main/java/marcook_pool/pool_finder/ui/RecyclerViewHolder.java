package marcook_pool.pool_finder.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import marcook_pool.pool_finder.R;
import marcook_pool.pool_finder.activites.OnePoolLocationActivity;

/**
 * Created by Carson on 18/09/2016.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String KEY_ESTABLISHMENT = "KEY_ESTABLISHMENT";
    public static final String KEY_DESCRIPTION = "KEY_DESCRIPTION";
    public static final String KEY_LOCATION = "KEY_LOCATION";
    public static final String KEY_RATING_BAR = "KEY_RATING_BAR";

    public TextView mEstablishment;
    public TextView mDescription;
    public TextView mLongDescription;
    public TextView mLocation;
    public RatingBar mRatingBar;

    private Context mContext;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mEstablishment = (TextView) itemView.findViewById(R.id.establishment);
        mDescription = (TextView) itemView.findViewById(R.id.description);
        mLongDescription = (TextView) itemView.findViewById(R.id.long_descrip);
        mLocation = (TextView) itemView.findViewById(R.id.location);
        mRatingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);
        mContext = mEstablishment.getContext(); //holder has no context, can't use getContext, but TextView does
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, OnePoolLocationActivity.class);
        intent.putExtra(KEY_ESTABLISHMENT, mEstablishment.getText().toString());
        String descrip = mDescription.getText().toString();
        if (descripContinues(descrip)) {
            intent.putExtra(KEY_DESCRIPTION, mLongDescription.getText().toString());
        } else {
            intent.putExtra(KEY_DESCRIPTION, mDescription.getText().toString());
        }
        intent.putExtra(KEY_LOCATION, mLocation.getText().toString());
        intent.putExtra(KEY_RATING_BAR, mRatingBar.getRating());
        mContext.startActivity(intent);
    }

    private boolean descripContinues(String descrip) { //descrip continues longer if ends in 3 periods
        return descrip.charAt(descrip.length() - 1) == '.' && descrip.charAt(descrip.length() - 2) == '.' && descrip.charAt(descrip.length() - 3) == '.';
    }
}
