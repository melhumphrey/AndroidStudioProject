package com.example.connectwithfriends.ui.contacts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectwithfriends.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Activity activity;
    ArrayList<ContactModel> arrayList;
    private RecyclerViewClickListener listener;

    public MainAdapter(Activity activity,ArrayList<ContactModel> arrayList, RecyclerViewClickListener listener) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.listener = listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel model = arrayList.get(position);

        holder.tvName.setText(model.getName());
        holder.tvNumber.setText(model.getNumber());

        if(model.getPhoto() != null) {
            Picasso.get().load(model.getPhoto()).into(holder.ivImage);
        } else {
            holder.ivImage.setImageResource(R.mipmap.ic_launcher_round);
        }
    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvNumber;
        CircleImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
            ivImage = itemView.findViewById(R.id.iv_image);
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