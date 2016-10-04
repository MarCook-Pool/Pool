package marcook_pool.pool_finder.fragments.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import marcook_pool.pool_finder.R;

/**
 * Created by Carson on 18/09/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    public static int MAX_DESCRIP_LENGTH = 30;

    private List<PoolTable> poolTable; //list of each person's data
    private Context context;

    public RecyclerViewAdapter(List<PoolTable> poolTable, Context context) {
        this.poolTable = poolTable;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        RecyclerViewHolder rcv = new RecyclerViewHolder(view);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.mEstablishment.setText(poolTable.get(position).getEstablishment());
        String description = poolTable.get(position).getDescription();
        if (description.length() <= MAX_DESCRIP_LENGTH) {
            holder.mDescription.setText(description);
        } else {
            String shortDescription = "";
            for (int i = 0; i < MAX_DESCRIP_LENGTH - 3; i++) { //leave room for 3 dots
                shortDescription += description.charAt(i);
            }
            shortDescription += "...";
            holder.mDescription.setText(shortDescription);
            holder.mLongDescription.setText(description);
        }
        holder.mLocation.setText(poolTable.get(position).getLocation());
        holder.mRatingBar.setRating(poolTable.get(position).getReview());
    }

    @Override
    public int getItemCount() {
        return this.poolTable.size();
    }
}
