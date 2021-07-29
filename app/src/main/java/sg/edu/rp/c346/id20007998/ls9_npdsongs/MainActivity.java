package sg.edu.rp.c346.id20007998.ls9_npdsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edTitle,edSingers,edYear;
    RadioGroup rgStars;
    Button btnInsert,btnShowList;
    RadioButton rg1,rg2,rg3,rg4,rg5;
    RatingBar rbStars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTitle=findViewById(R.id.etSong);
        edSingers=findViewById(R.id.etSingers);
        edYear=findViewById(R.id.etYear);
        //rgStars=findViewById(R.id.rgStars);
        btnInsert=findViewById(R.id.btnInsert);
        btnShowList=findViewById(R.id.btnShowList);
        rbStars=findViewById(R.id.ratingStars_home);

        /*rg1=findViewById(R.id.rgS1);
        rg2=findViewById(R.id.rgS2);
        rg3=findViewById(R.id.rgS3);
        rg4=findViewById(R.id.rgS4);
        rg5=findViewById(R.id.rgS5);*/




        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data_title = edTitle.getText().toString();
                String data_singers=edSingers.getText().toString();
                int data_year=Integer.parseInt(edYear.getText().toString());
                float data_stars=0;
                data_stars=rbStars.getRating();
                /*if (rgStars.getCheckedRadioButtonId()==rg1.getId()){
                    data_stars=1;
                }else if(rgStars.getCheckedRadioButtonId()==rg2.getId()){
                    data_stars=2;
                }else if(rgStars.getCheckedRadioButtonId()==rg3.getId()){
                    data_stars=3;
                }else if(rgStars.getCheckedRadioButtonId()==rg4.getId()){
                    data_stars=4;
                }else if(rgStars.getCheckedRadioButtonId()==rg5.getId()) {
                    data_stars = 5;
                }*/

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(data_title,data_singers,data_year,data_stars);

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
                edTitle.setText("");
                edYear.setText("");
                edSingers.setText("");
                rbStars.setRating(0);
            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        ListViewActivity.class);
                startActivity(i);
            }
        });

    }

}