package com.example.admin.cinema2nf;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Optimus on 4/7/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<MovieInfo> movieInfoList;
    Context context;
    FragmentManager fragmentManager;

    public MovieAdapter(List<MovieInfo> movieInfoList) {
        this.movieInfoList = movieInfoList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView movie_name, movie_release, movie_imdb, movie_duration, movie_id;
        public ImageView img_poster, img_age, img_format;

        public MyViewHolder(View v) {
            super(v);
            movie_name = (TextView) v.findViewById(R.id.movie_name);
            movie_release = (TextView) v.findViewById(R.id.movie_date_release);
            movie_duration = (TextView) v.findViewById(R.id.movie_duration);
            movie_imdb = (TextView) v.findViewById(R.id.movie_imdb_point);
            movie_id = (TextView) v.findViewById(R.id.movie_id);

            img_age = (ImageView) v.findViewById(R.id.img_movie_age);
            img_format = (ImageView) v.findViewById(R.id.img_movie_format);
            img_poster = (ImageView) v.findViewById(R.id.img_movie_poster);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovieInfo movie = movieInfoList.get(position);
        Picasso.with(context).load(movie.getPosterurl()).into(holder.img_poster);
        holder.movie_id.setText(String.valueOf(movie.getId()));
        holder.movie_name.setText(String.valueOf(movie.getName()));
        holder.movie_imdb.setText("IMDb: " + String.valueOf(movie.getImdb()) + "/10");
        holder.movie_release.setText("Ngày khởi chiếu: " + String.valueOf(movie.getRelease()));
        holder.movie_duration.setText("Thời lượng: " + String.valueOf(movie.getDuration()) + " phút");

        if (movie.getAge() == 1) {
            holder.img_age.setImageResource(R.drawable.p);
            if (movie.getAge() == 2) {
                holder.img_age.setImageResource(R.drawable.c13);
            } else {
                if (movie.getAge() == 3) {
                    holder.img_age.setImageResource(R.drawable.c16);
                } else {
                    holder.img_age.setImageResource(R.drawable.c18);
                }
            }
        }
        if (movie.getFormat() == 1) {
            holder.img_format.setImageResource(R.drawable.bg2d);
        } else {
            holder.img_format.setImageResource(R.drawable.bg3d);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView tv_id = (TextView)v.findViewById(R.id.movie_id);
                Fragment fragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", tv_id.getText().toString());

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new MovieDetailFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, myFragment).addToBackStack(null).commit();
                /*fragment.setArguments(bundle);
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new SigninFragment())
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();*/
            }
        });
    }


    @Override
    public int getItemCount() {
        return movieInfoList.size();
    }
}
