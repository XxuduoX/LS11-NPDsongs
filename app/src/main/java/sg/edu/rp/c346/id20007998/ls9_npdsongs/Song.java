package sg.edu.rp.c346.id20007998.ls9_npdsongs;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private float stars;

    public Song(String title,String singers,int year,float stars){
        this.title=title;
        this.singers=singers;
        this.year=year;
        this.stars=stars;
    }
    public Song(int id,String title,String singers,int year,float stars){
        this.id=id;
        this.title=title;
        this.singers=singers;
        this.year=year;
        this.stars=stars;
    }

    public int getId(){
        return  id;
    }
    public String getTitle(){
        return  title;
    }

    public String getSingers(){
        return singers;
    }
    public int getYear(){
        return year;
    }
    public float getStars(){
        return stars;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setSingers(String singers){
        this.singers=singers;
    }

    public void setYear(int year){
        this.year=year;
    }

    public void setStars(float stars){
        this.stars=stars;
    }



    /*@Override
    public String toString() {
        String statement="";
        if(stars==1){
            statement= title + "\n" + singers +"-"+year +"\n " + "*";
        }else if(stars==2){
            statement= title + "\n" + singers +"-"+year +"\n " + "* *";
        }else if(stars==3){
            statement= title + "\n" + singers +"-"+year +"\n " + "* * *";
        }else if(stars==4){
            statement= title + "\n" + singers +"-"+year +"\n " + "* * * *";
        }else if(stars==5){
            statement= title + "\n" + singers +"-"+year +"\n " + "* * * * *";
        }
        return statement;

    }*/
}
