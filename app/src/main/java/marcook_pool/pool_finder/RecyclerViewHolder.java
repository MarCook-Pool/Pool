package marcook_pool.pool_finder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

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
    public TextView mLocation;
    public RatingBar mRatingBar;

    private Context mContext;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mEstablishment = (TextView) itemView.findViewById(R.id.establishment);
        mDescription = (TextView) itemView.findViewById(R.id.description);
        mLocation = (TextView) itemView.findViewById(R.id.location);
        mRatingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);
        mContext = mEstablishment.getContext(); //holder has no context, can't use getContext, but TextView does
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, OnePoolLocationActivity.class);
        intent.putExtra(KEY_ESTABLISHMENT,mEstablishment.getText());
        intent.putExtra(KEY_DESCRIPTION,mDescription.getText());
        intent.putExtra(KEY_LOCATION,mLocation.getText());
        intent.putExtra(KEY_RATING_BAR,mRatingBar.getRating());
        mContext.startActivity(intent);
    }
}
