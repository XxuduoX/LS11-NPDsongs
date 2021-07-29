package sg.edu.rp.c346.id20007998.ls9_npdsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> songList) {
        super(context, resource, songList);

        this.context=context;
        this.layout_id=resource;
        this.songList=songList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //R.Layout.row
        View rowView = inflater.inflate(layout_id,parent,false);
        //inflate the view for each row
        //line 32 and 34 will always exist and has no change to it.
        TextView tvTitle=rowView.findViewById(R.id.tvSongTitle);
        TextView tvYear=rowView.findViewById(R.id.tvYears);
        //TextView tvStar=rowView.findViewById(R.id.tvStar);
        TextView tvSinger=rowView.findViewById(R.id.tvSinger);
        ImageView ivNew=rowView.findViewById(R.id.imNew);
        RatingBar rbStars=rowView.findViewById(R.id.ratingStarsListView);
        //Obtain UI component and do the necessary binding.

        Song currentRow=songList.get(position);

        tvTitle.setText(currentRow.getTitle());
        Integer intYear=currentRow.getYear();
        String strYear=intYear.toString();
        tvYear.setText(strYear);
        tvSinger.setText(currentRow.getSingers());
        rbStars.setRating(currentRow.getStars());
        //String stars="";

        if(intYear<2019){
            ivNew.setVisibility(View.INVISIBLE);
        }

        /*if(currentRow.getStars()==1){
            stars=" *";
        }else if(currentRow.getStars()==2){
            stars=" * *";
        }else if(currentRow.getStars()==3) {
            stars = " * * *";
        }else if(currentRow.getStars()==4) {
            stars = " * * * *";
        }else if(currentRow.getStars()==5) {
            stars = " * * * * * ";
        }
        tvStar.setText(stars);*/

        return rowView;
    }
}
