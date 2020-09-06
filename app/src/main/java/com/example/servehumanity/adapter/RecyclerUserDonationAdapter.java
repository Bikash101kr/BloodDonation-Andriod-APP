package com.example.servehumanity.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servehumanity.Activity.DonationDetailActivity;
import com.example.servehumanity.R;
import com.example.servehumanity.api.DonateBloodAPI;
import com.example.servehumanity.model.DonateBlood;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerUserDonationAdapter extends RecyclerView.Adapter<RecyclerUserDonationAdapter.DonateBloodViewHolder>{
    Context context;
    List<DonateBlood> donateBloodList;

    public RecyclerUserDonationAdapter(Context context, List<DonateBlood> donateBloodList) {
        this.context = context;
        this.donateBloodList = donateBloodList;
    }

    @NonNull
    @Override
    public DonateBloodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_donate_blood_view, parent, false);
        return new DonateBloodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DonateBloodViewHolder holder, final int position) {
        final DonateBlood donateBlood = donateBloodList.get(position);
        holder.tvLocation.setText(donateBlood.getLocation());
        holder.tvDate.setText(donateBlood.getDonationDate());



        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAPI(donateBloodList.get(position).get_id());
            }
        });
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DonationDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("_id", donateBlood.get_id());
                context.startActivity(intent);
            }

        });
    }



    private void callAPI(String id) {
        donateBloodList.clear();
        final DonateBloodAPI donateBloodAPI = com.example.servehumanity.Url.URL.getInstance().create(DonateBloodAPI.class);
        Call<Void> call = donateBloodAPI.delete_userDonation(id,com.example.servehumanity.Url.URL.token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Unsuccessful!", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "There is no donations to display!", Toast.LENGTH_LONG).show();
                Log.i("onFailure", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return donateBloodList.size();
    }

    public class DonateBloodViewHolder extends RecyclerView.ViewHolder{
        ImageView imgDelete;
        TextView tvLocation, tvDate;
        ConstraintLayout constraintLayout;

        public DonateBloodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDelete = itemView.findViewById(R.id.imgDelete);

           tvLocation = itemView.findViewById(R.id.tvLocation);
           tvDate = itemView.findViewById(R.id.tvDate);
           constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }}

