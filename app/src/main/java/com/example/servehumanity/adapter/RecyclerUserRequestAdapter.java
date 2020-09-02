package com.example.servehumanity.adapter;

import android.content.Context;
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

import com.example.servehumanity.R;
import com.example.servehumanity.api.RequestBloodAPI;
import com.example.servehumanity.model.RequestBlood;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerUserRequestAdapter extends RecyclerView.Adapter<RecyclerUserRequestAdapter.RequestBloodViewHolder>{
    Context context;
    List<RequestBlood> requestBloodList;

    public RecyclerUserRequestAdapter(Context context, List<RequestBlood> requestBloodList) {
        this.context = context;
        this.requestBloodList = requestBloodList;
    }

    @NonNull
    @Override
    public RequestBloodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_request_blood_view, parent, false);
        return new RequestBloodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RequestBloodViewHolder holder, int position) {
        final RequestBlood requestBlood = requestBloodList.get(position);
        holder.tvLocation.setText(requestBlood.getHospitalName());
        holder.tvDate.setText(requestBlood.getRequestDate());
        holder.tvName.setText(requestBlood.getPatientName());
        holder.tvBloodGroup.setText(requestBlood.getBloodGroup());
        holder.tvAddress.setText(requestBlood.getFullAddress());

        final int pos = position;

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAPI(requestBloodList.get(pos).get_id());
                holder.constraintLayout.setVisibility(View.GONE);
            }
        });
    }

    private void callAPI(String id) {
        requestBloodList.clear();
        final RequestBloodAPI requestBloodAPI = com.example.servehumanity.Url.URL.getInstance().create(RequestBloodAPI.class);
        Call<Void> call = requestBloodAPI.delete_userRequest(com.example.servehumanity.Url.URL.token);
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
                Toast.makeText(context, "There is no requests to display!", Toast.LENGTH_LONG).show();
                Log.i("onFailure", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestBloodList.size();
    }

    public class RequestBloodViewHolder extends RecyclerView.ViewHolder{
        ImageView imgDelete;
        TextView tvLocation, tvDate, tvBloodGroup, tvName, tvAddress;
        ConstraintLayout constraintLayout;

        public RequestBloodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            tvName = itemView.findViewById(R.id.tvName);
            tvBloodGroup = itemView.findViewById(R.id.tvBloodGroup);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvDate = itemView.findViewById(R.id.tvDate);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }}

