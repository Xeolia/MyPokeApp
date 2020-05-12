package com.example.ghibliapp.presentation.Vue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ghibliapp.MySecondFragment;
import com.example.ghibliapp.R;
import com.example.ghibliapp.presentation.Modele.Ghibli;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private List<Ghibli> values ;

        class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            TextView txtHeader;
            TextView txtFooter;
            View layout;

            ViewHolder(View v) {
                super(v);
                layout = v;
                txtHeader = (TextView) v.findViewById(R.id.firstLine);
                txtFooter = (TextView) v.findViewById(R.id.secondLine);
            }
        }

        public void add(int position, Ghibli item) {
            values.add(position, item);
            notifyItemInserted(position);
        }

        private void remove(int position) {
            values.remove(position);
            notifyItemRemoved(position);
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ListAdapter(List<Ghibli> myDataset) {
            values = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
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

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            final Ghibli currentGhibli = values.get(position);

            holder.txtHeader.setText(currentGhibli.getTitle());
            holder.txtFooter.setText(currentGhibli.getDescription());
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context Mycontext = v.getContext();
                    Intent intent = new Intent(Mycontext, MySecondFragment.class);
                    intent.putExtra("Titre",currentGhibli.getTitle());
                    intent.putExtra("FilmDirector",currentGhibli.getDirector());
                    intent.putExtra("Description",currentGhibli.getDescription());
                    intent.putExtra("Date",currentGhibli.getRelease_date());

                    Mycontext.startActivity(intent);

                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return values.size();
        }

}

