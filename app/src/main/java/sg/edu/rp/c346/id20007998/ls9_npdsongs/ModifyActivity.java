package sg.edu.rp.c346.id20007998.ls9_npdsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public class ModifyActivity extends AppCompatActivity {
    EditText edTitle,edSingers,edYear,edID;
    RadioGroup rgStars;
    Button btnUpdate,btnDelete,btnCancel;
    RatingBar rbStars;
    //RadioButton rg1,rg2,rg3,rg4,rg5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        edTitle=findViewById(R.id.etSong);
        edSingers=findViewById(R.id.etSingers);
        edYear=findViewById(R.id.etYear);
        edID=findViewById(R.id.etID);
        //rgStars=findViewById(R.id.rgStars);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        btnCancel=findViewById(R.id.btnCancel);
        rbStars=findViewById(R.id.ratingStars_modify);
        /*rg1=findViewById(R.id.rgS1);
        rg2=findViewById(R.id.rgS2);
        rg3=findViewById(R.id.rgS3);
        rg4=findViewById(R.id.rgS4);
        rg5=findViewById(R.id.rgS5);*/

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");
        edID.setText(data.getId()+"");
        edTitle.setText(data.getTitle());
        edSingers.setText(data.getSingers());
        edYear.setText(data.getYear()+"");
        rbStars.setRating(data.getStars());
        //int edStars=data.getStars();

        edID.setEnabled(false);

        /*if(edStars==1){
            rg1.setChecked(true);
        }else if(edStars==2){
            rg2.setChecked(true);
        }else if(edStars==3) {
            rg3.setChecked(true);
        }else if(edStars==4) {
            rg4.setChecked(true);
        }else if(edStars==5) {
            rg5.setChecked(true);
        }*/

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setTitle(edTitle.getText().toString().trim());
                data.setSingers(edSingers.getText().toString().trim());
                int year=Integer.valueOf(edYear.getText().toString().trim());
                data.setYear(year);

               /* int selectRB=rgStars.getCheckedRadioButtonId();
                RadioButton rb=(RadioButton)findViewById(selectRB);
                data.setStars(Integer.parseInt(rb.getText().toString()));*/
                float stars=rbStars.getRating();
                data.setStars(stars);
                dbh.updateSong(data);
                dbh.close();

                finish();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteSong(data.getId());

                finish();

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}