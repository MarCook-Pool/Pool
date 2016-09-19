package marcook_pool.pool_finder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Carson on 18/09/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
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
        holder.mDescription.setText(poolTable.get(position).getDescription());
        holder.mDistance.setText(poolTable.get(position).getLocation());
        holder.mRatingBar.setNumStars(poolTable.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return this.poolTable.size();
    }
}
