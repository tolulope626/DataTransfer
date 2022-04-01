package com.example.datatransfer;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class StudentVH extends RecyclerView.ViewHolder {

    public TextView edt_firstName, edt_lastName;
    public CardView cardView;

    public StudentVH(@NonNull View itemView){
        super(itemView);
        edt_firstName = itemView.findViewById(R.id.firstName);
        edt_lastName = itemView.findViewById(R.id.lastName);
        cardView = itemView.findViewById(R.id.CardView);


    }
}
