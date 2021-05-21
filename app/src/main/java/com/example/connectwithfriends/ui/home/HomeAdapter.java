package com.example.connectwithfriends.ui.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectwithfriends.R;

import java.util.ArrayList;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Activity activity;
    ArrayList<NotificationItemModel> arrayList;
    private RecyclerViewClickListener listener;

    public HomeAdapter(Activity activity, ArrayList<NotificationItemModel> arrayList, RecyclerViewClickListener listener) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.listener = listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationItemModel model = arrayList.get(position);
        holder.name.setText(model.getName());
        holder.text.setText(model.getText());
        holder.timestamp.setText(model.getTimestamp());

    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, text, timestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            text = itemView.findViewById(R.id.text);
            timestamp = itemView.findViewById(R.id.timestamp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
