package com.example.datatransfer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Data extends AppCompatActivity {
    private TextView txtFirstName, txtLastName, txtAge, txtWeight, txtHeight, txtForcePunchResult, txtPunchData;
    private Button punchButton;
    private NavController navController;
    private Resources res;
    private long accountID;

    @Exclude
    private String key;
    private String punchKey;
    private ArrayList<String> punchKeys = new ArrayList<String>();
    private ArrayList<PunchModel> punches = new ArrayList<>();


    private int count;
    private boolean isSame;
    private String sFname, sLname, sAge, sWeight, sHeight;
    private FBDBHelper fbdbHelper;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        txtFirstName = findViewById(R.id.TxtFirstName);
        txtLastName = findViewById(R.id.TxtLastName);
        txtAge = findViewById(R.id.TxtAge);
        txtWeight = findViewById(R.id.TxtWeight);
        txtHeight = findViewById(R.id.TxtHeight);
        txtForcePunchResult = findViewById(R.id.TxtPunchForceResult);
        punchButton = findViewById(R.id.punchButton);
        txtPunchData = findViewById(R.id.TxtPunchData);
        res = getResources();

        ProfileModel student = (ProfileModel) getIntent().getParcelableExtra("Data");
        txtFirstName.setText(student.getFirstName());
        txtLastName.setText(student.getLastName());
        txtAge.setText(student.getAge());
        float weight = student.getWeight();
        float height =  student.getHeight();
        txtWeight.setText(String.valueOf(weight));
        txtHeight.setText(String.valueOf(height));
        accountID = student.getId();
        fbdbHelper = new FBDBHelper();
        loadPunchData(accountID);

        if (fbdbHelper != null){
            punchButton.setOnClickListener(v ->
            {
                StringBuilder display = new StringBuilder();

                if (punches != null){

                    for (int i = 0; i < count; i++) {
                        display.append(punches.get(i).toString(i+1, res.getString(R.string.date_format), res.getString(R.string.number_format)));

                    }
                    txtPunchData.setText(String.valueOf(display));
                }
                else{
                    txtPunchData.setText("No punch recorded");
                    txtForcePunchResult.setText("No punch recorded");
                }
            });
        }
        /*
        loadPunchData(accountID);
        StringBuilder display = new StringBuilder();

        if (punches != null){
            for (int i = 0; i < punches.size(); i++) {
                display.append(punches.get(i).toString(i+1, res.getString(R.string.date_format), res.getString(R.string.number_format)));
            }

            txtPunchData.setText(display.toString());
        }

         */


    }


    private void loadPunchData(long accountID) {


        fbdbHelper.getPunch(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double max = 0;
                for(DataSnapshot data : snapshot.getChildren()){
                    PunchModel punch = data.getValue(PunchModel.class);
                    if(punch != null){
                        if(punch.getAccountID() == accountID)
                        {
                            key = data.getKey().toString();
                            punchKeys.add(key);
                            punches.add(punch);
                            //max = punches.get(0).getForce();
                            if(punches.get(count).getForce() > max ){
                                max = punches.get(count).getForce();
                            }
                            count++;
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Wrong USER", Toast.LENGTH_SHORT).show();
                    }
                }
                txtForcePunchResult.setText(df.format(max));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}