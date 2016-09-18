package marcook_pool.pool_finder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Carson on 18/09/2016.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{ //class for each person's info
    public TextView mEstablishment;
    public TextView mDistance;
    public RatingBar mRatingBar;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mEstablishment = (TextView) itemView.findViewById(R.id.establishment);
      //  mDistance = (TextView) itemView.findViewById(R.id.distance);
      //  mRatingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);
    }
    @Override
    public void onClick(View view) {
    }
}
