package com.example.admin.cinema2nf;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import static java.lang.System.load;

/**
 * Created by Optimus on 4/12/2017.
 */

public class MovieDetailFragment extends Fragment implements YouTubePlayer.OnInitializedListener{
    View myView;
    public String API_KEY = "AIzaSyBZj3624Oh1C0-sib0dqQn0xPGqdEA5LCk";
    public String VIDEO_ID = "rJdTUnrrISk";
    private TextView name, duration, director, actor, country, language, genres, date, imdb, format, content;
    private ImageView poster;
    private YouTubePlayerView trailer;
    private Button btn_Booking;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.movie_detail,container,false);
        name = (TextView) myView.findViewById(R.id.txt_movie_detail_name);
        duration = (TextView) myView.findViewById(R.id.txt_movie_detail_duration);
        director = (TextView) myView.findViewById(R.id.txt_movie_detail_director);
        actor = (TextView) myView.findViewById(R.id.txt_movie_detail_actor);
        country = (TextView) myView.findViewById(R.id.txt_movie_detail_country);
        language = (TextView) myView.findViewById(R.id.txt_movie_detail_language);
        genres = (TextView) myView.findViewById(R.id.txt_movie_detail_genres);
        date = (TextView) myView.findViewById(R.id.txt_movie_detail_date_release);
        imdb = (TextView) myView.findViewById(R.id.txt_movie_detail_imdb_point);
        format = (TextView) myView.findViewById(R.id.txt_movie_detail_format);
        poster = (ImageView) myView.findViewById(R.id.img_movie_detail_poster);
        trailer = (YouTubePlayerView) myView.findViewById(R.id.vid_movie_detail_trailer);
        content = (TextView) myView.findViewById(R.id.txt_movie_detail_content);


        Bundle i = getArguments();
        name.setText(i.getString("name"));
        duration.setText("Duration: " + i.getString("duration") + " mins");
        director.setText("Director: " + i.getString("director"));
        actor.setText("Actors and Actresses: " + i.getString("actornactress"));
        country.setText("Nation: " + i.getString("nation"));
        language.setText("Language: " + i.getString("language"));
        genres.setText("Genres: " + i.getString("category"));
        date.setText("Release date: " + i.getString("startday"));
        imdb.setText("IMDb: " + i.getString("imdb") + "/10");
        if (i.getString("format") == "1") {
            format.setText("Format: 2D");
        } else {
            format.setText("Format: 3D");
        }
        VIDEO_ID = i.getString("urltrailer");
        VIDEO_ID = VIDEO_ID.split("v=")[1];

        content.setText(i.getString("content"));
        trailer.initialize(API_KEY, MovieDetailFragment.this);




        Picasso.with(getActivity().getBaseContext()).load(i.getString("poster")).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(poster);


        return myView;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
