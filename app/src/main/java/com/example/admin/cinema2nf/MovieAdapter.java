package com.example.admin.cinema2nf;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Optimus on 4/7/2017.
 */

public class MovieAdapter extends ArrayAdapter<MovieInfo> {

    Context context;
    int layout;
    ArrayList<MovieInfo> data;

    public MovieAdapter(Context context, int resource, ArrayList<MovieInfo> object) {
        super(context, resource, object);
        this.context = context;
        layout = resource;
        data = object;
    }

    public View getView(int position, View v, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //View v = context.getLayoutInflater().inflate(layout, null);
        if (v == null) {
            v = mInflater.inflate(R.layout.movie_item, null);
        }
        ImageView img_movie_poster = (ImageView) v.findViewById(R.id.img_movie_poster);
        Picasso.with(getContext()).load(data.get(position).getPosterurl()).into(img_movie_poster);

        TextView movie_name = (TextView) v.findViewById(R.id.movie_name);
        movie_name.setText(String.valueOf(data.get(position).getName()));

        TextView movie_release = (TextView) v.findViewById(R.id.movie_date_release);
        movie_release.setText("Release Date: " + String.valueOf(data.get(position).getRelease()));

        TextView movie_imdb = (TextView) v.findViewById(R.id.movie_imdb_point);
        movie_imdb.setText("IMDb: " + String.valueOf(data.get(position).getImdb()));

        TextView movie_duration = (TextView) v.findViewById(R.id.movie_duration);
        movie_duration.setText("Duration: " + String.valueOf(data.get(position).getDuration()) + "mins");

        ImageView img_movie_age = (ImageView) v.findViewById(R.id.img_movie_age);
        if (data.get(position).getAge() == 1) {
            img_movie_age.setImageResource(R.drawable.p);
        } else {
            if (data.get(position).getAge() == 2) {
                img_movie_age.setImageResource(R.drawable.c13);
            } else {
                if (data.get(position).getAge() == 3) {
                    img_movie_age.setImageResource(R.drawable.c16);
                } else {
                    img_movie_age.setImageResource(R.drawable.c18);
                }
            }
        }

        ImageView img_movie_format = (ImageView) v.findViewById(R.id.img_movie_format);
        if (data.get(position).getFormat() == 1) {
            img_movie_format.setImageResource(R.drawable.bg2d);
        } else {
            img_movie_format.setImageResource(R.drawable.bg3d);
        }


        return v;
    }
}
//package com.example.admin.cinema2nf;
//
//import android.app.Activity;
//import android.content.Context;
//import android.support.v4.app.Fragment;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
///**
// * Created by Optimus on 4/7/2017.
// */
//
//public class MovieAdapter extends ArrayAdapter {
//
//    Fragment activity;
//    int layout;
//    ArrayList<MovieInfo> data;
//
//    public MovieAdapter(MovieAllFragment context, int resource, ArrayList<MovieInfo> object) {
//        super(context(), resource, object);
//        activity = context;
//        layout = resource;
//        data = object;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = activity.getLayoutInflater().inflate(layout, null);
//
//        ImageView img_movie_poster = (ImageView) v.findViewById(R.id.img_movie_poster);
//        Picasso.with(getContext()).load(data.get(position).getPosterurl()).into(img_movie_poster);
//
//        TextView movie_name = (TextView) v.findViewById(R.id.movie_name);
//        movie_name.setText(String.valueOf(data.get(position).getName()));
//
//        TextView movie_release = (TextView) v.findViewById(R.id.movie_date_release);
//        movie_release.setText("Release Date: " + String.valueOf(data.get(position).getRelease()));
//
//        TextView movie_imdb = (TextView) v.findViewById(R.id.movie_imdb_point);
//        movie_imdb.setText("IMDb: " + String.valueOf(data.get(position).getImdb()));
//
//        TextView movie_duration = (TextView) v.findViewById(R.id.movie_duration);
//        movie_duration.setText("Duration: " + String.valueOf(data.get(position).getDuration()) + "mins");
//
//        ImageView img_movie_age = (ImageView) v.findViewById(R.id.img_movie_age);
//        if (data.get(position).getAge() == 1) {
//            img_movie_age.setImageResource(R.drawable.p);
//        } else {
//            if (data.get(position).getAge() == 2) {
//                img_movie_age.setImageResource(R.drawable.c13);
//            } else {
//                if (data.get(position).getAge() == 3) {
//                    img_movie_age.setImageResource(R.drawable.c16);
//                }else{
//                    img_movie_age.setImageResource(R.drawable.c18);
//                }
//            }
//        }
//
//        ImageView img_movie_format = (ImageView) v.findViewById(R.id.img_movie_format);
//        if (data.get(position).getFormat() == 1){
//            img_movie_format.setImageResource(R.drawable.bg2d);
//        }else{
//            img_movie_format.setImageResource(R.drawable.bg3d);
//        }
//
//
//        return v;
//    }
//}
