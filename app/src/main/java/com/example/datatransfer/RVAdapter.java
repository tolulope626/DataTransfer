package com.example.datatransfer;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<ProfileModel> profileModelList = new ArrayList<>();
    ArrayList<PunchModel> punchList = new ArrayList<>();

    public RVAdapter(Context ctx){
        this.context = ctx;
    }

    public void setItems(ArrayList<ProfileModel> profileModels){
        profileModelList.addAll(profileModels);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new StudentVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StudentVH vh = (StudentVH) holder;
        ProfileModel profilemodel = profileModelList.get(position);
        vh.edt_firstName.setText(profilemodel.getFirstName());
        vh.edt_lastName.setText(profilemodel.getLastName());
        vh.cardView.setOnClickListener(v->
        {
            Intent intent = new Intent(context, Data.class);
            intent.putExtra("Data", profilemodel);
            context.startActivity(intent);


        });
    }

    @Override
    public int getItemCount() {
        return profileModelList.size();
    }
}
