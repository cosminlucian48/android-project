package com.example.proiect_real;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TracksViewHolder> {

    private static final String TAG = TrackAdapter.class.getSimpleName();
    private List<Track> tracks;

    TrackAdapter(List<Track> tracksList) {
        this.tracks = tracksList;
    }

    @NonNull
    @Override
    public TracksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder()");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new TracksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TracksViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder(), position= " + position);
        String name = tracks.get(position).getName();
        holder.name.setText(name);

        String city = tracks.get(position).getCity();
        holder.city.setText(city);

        String length = tracks.get(position).getLength();
        holder.length.setText(length);

    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    static class TracksViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView city;
        private TextView length;

        TracksViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            city = itemView.findViewById(R.id.city);
            length = itemView.findViewById(R.id.length);
        }
    }
}
