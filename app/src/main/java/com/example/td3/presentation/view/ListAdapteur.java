package com.example.td3.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.td3.R;
import com.example.td3.presentation.model.Film;

import java.util.List;

public class ListAdapteur extends RecyclerView.Adapter<ListAdapteur.ViewHolder> {

    private List<Film> values;
    private OnItemClickListener listener;


    //objet listener : OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(Film item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, Film item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public ListAdapteur(List<Film> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }

    @Override
    public ListAdapteur.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Film currentStudio = values.get(position);
        holder.txtHeader.setText(currentStudio.getTitre());//ce qui appel le titre
        holder.txtFooter.setText("Film d'animation ");//ce qui appelle la deuxieme ligne

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(currentStudio);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
