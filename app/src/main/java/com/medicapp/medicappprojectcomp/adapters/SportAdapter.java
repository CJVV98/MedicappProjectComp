package com.medicapp.medicappprojectcomp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.medicapp.medicappprojectcomp.R;

import java.util.ArrayList;
import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder>{


    private List<Sport> sportList;
    private final Context context;
    RecyclerView recyclerView;
    SportAdapter sportAdapter;

    public SportAdapter(List<Sport> sportList, Context context) {
        this.sportList = sportList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_view, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        String url = sportList.get(pos).getSportPng();
        String nameCountry = sportList.get(pos).getName().toString();
        String capital = sportList.get(pos).getNativeName().toString();
        holder.txtCountry.setText(nameCountry);
        holder.txtCapital.setText(capital);

        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(holder.imageSport);

    }


    @Override
    public int getItemCount() {
        return sportList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageSport  ;
        private TextView txtCountry;
        private TextView txtCapital;
        private TextView txtMoney;

        private FloatingActionButton btnCall;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSport = itemView.findViewById(R.id.image_sport);
            txtCountry = itemView.findViewById(R.id.txt_country);
            txtCapital = itemView.findViewById(R.id.txt_capital);
            txtMoney = itemView.findViewById(R.id.txt_money);
            btnCall =  itemView.findViewById(R.id.floatingActionButton);

        }
    }
}
