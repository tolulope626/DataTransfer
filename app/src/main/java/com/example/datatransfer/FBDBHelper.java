package com.example.datatransfer;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FBDBHelper {

    private DatabaseReference databaseReference;
    private DatabaseReference punchDatabaseReference;

    public FBDBHelper(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(ProfileModel.class.getSimpleName());
        punchDatabaseReference = db.getReference(PunchModel.class.getSimpleName());
    }

    public Query get(String key){
        if(key == null){
            return databaseReference.orderByKey();
        }
        return databaseReference.orderByKey().startAfter(key);
    }


    public Query getPunch(String key){
        if(key == null){
            return punchDatabaseReference.orderByKey();
        }
        return punchDatabaseReference.orderByKey().startAfter(key);
    }
}
