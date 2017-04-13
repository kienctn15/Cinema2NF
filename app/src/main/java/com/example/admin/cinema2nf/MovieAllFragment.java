package com.example.admin.cinema2nf;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Optimus on 4/12/2017.
 */

public class MovieAllFragment extends ListFragment {
    View myView;
    FragmentManager fragmentManager;
    private ListView listview;
    private MovieAdapter adapter;
    private ArrayList<MovieInfo> arrayList;
    private Socket mSocket;
    private JSONArray jsonarray;
    private Emitter.Listener getData = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        arrayList = new ArrayList<MovieInfo>();
                        jsonarray = data.getJSONArray("listmovie");
                        for (int i = 0; i < jsonarray.length(); i++) {
                            MovieInfo movies = new MovieInfo(
                                    jsonarray.getJSONObject(i).getString("name"),
                                    jsonarray.getJSONObject(i).getString("startday"),
                                    jsonarray.getJSONObject(i).getDouble("imdb"),
                                    jsonarray.getJSONObject(i).getInt("duration"),
                                    "http://192.168.0.32:3000" + jsonarray.getJSONObject(i).getString("image"),
                                    jsonarray.getJSONObject(i).getInt("ages"),
                                    jsonarray.getJSONObject(i).getInt("format")
                            );
                            arrayList.add(movies);
                        }
                        setMoviesList();
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };

    {
        try {
            mSocket = IO.socket("http://192.168.0.32:3000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public static MovieAllFragment newInstance(){
       MovieAllFragment movieAllFragment = new MovieAllFragment();

       return movieAllFragment;
   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_movie_all, container, false);

        listview = (ListView) myView.findViewById(R.id.movie_list_view);
        mSocket.connect();
        mSocket.on("data", getData);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new SignupFragment())
                        .addToBackStack(null)
                        .commit();
                MovieAllFragment movieAllFragment = new MovieAllFragment();
                Bundle i = new Bundle();

                try {
                    i.putString("name", jsonarray.getJSONObject(position).getString("name"));
                    i.putString("duration", String.valueOf(jsonarray.getJSONObject(position).getInt("duration")));
                    i.putString("director", jsonarray.getJSONObject(position).getString("director"));
                    i.putString("actornactress", jsonarray.getJSONObject(position).getString("actornactress"));
                    i.putString("nation", jsonarray.getJSONObject(position).getString("nation"));
                    i.putString("language", jsonarray.getJSONObject(position).getString("language"));
                    i.putString("category", jsonarray.getJSONObject(position).getString("category"));
                    i.putString("startday", jsonarray.getJSONObject(position).getString("startday"));
                    i.putString("format", jsonarray.getJSONObject(position).getString("format"));
                    i.putString("imdb", String.valueOf(jsonarray.getJSONObject(position).getDouble("imdb")));
                    i.putString("urltrailer", jsonarray.getJSONObject(position).getString("urltrailer"));
                    i.putString("content", jsonarray.getJSONObject(position).getString("content"));
                    i.putString("poster", "http://192.168.0.32:3000" + jsonarray.getJSONObject(position).getString("image"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                movieAllFragment.setArguments(i);
            }
        });
        return myView;
    }

    private void setMoviesList() {
        adapter = new MovieAdapter(getActivity(), R.layout.movie_item, arrayList);
        listview.setAdapter(adapter);
    }

}
//package com.example.admin.cinema2nf;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import com.github.nkzawa.emitter.Emitter;
//import com.github.nkzawa.socketio.client.IO;
//import com.github.nkzawa.socketio.client.Socket;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
///**
// * Created by Optimus on 4/12/2017.
// */
//
//public class MovieAllFragment extends Fragment {
//    View myView;
//    FragmentManager fragmentManager;
//    private ListView listview;
//    private MovieAdapter adapter;
//    private ArrayList<MovieInfo> arrayList;
//    private Socket mSocket;
//    private JSONArray jsonarray;
//    private Emitter.Listener getData = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    JSONObject data = (JSONObject) args[0];
//                    try {
//                        arrayList = new ArrayList<MovieInfo>();
//                        jsonarray = data.getJSONArray("listmovie");
//                        for (int i = 0; i < jsonarray.length(); i++) {
//                            MovieInfo movies = new MovieInfo(
//                                    jsonarray.getJSONObject(i).getString("name"),
//                                    jsonarray.getJSONObject(i).getString("startday"),
//                                    jsonarray.getJSONObject(i).getDouble("imdb"),
//                                    jsonarray.getJSONObject(i).getInt("duration"),
//                                    "http://192.168.0.32:3000" + jsonarray.getJSONObject(i).getString("image"),
//                                    jsonarray.getJSONObject(i).getInt("ages"),
//                                    jsonarray.getJSONObject(i).getInt("format")
//                            );
//                            arrayList.add(movies);
//                        }
//                        setMoviesList();
//                    } catch (JSONException e) {
//                        return;
//                    }
//                }
//            });
//        }
//    };
//
//    {
//        try {
//            mSocket = IO.socket("http://192.168.0.32:3000");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//   public static MovieAllFragment newInstance(){
//       MovieAllFragment movieAllFragment = new MovieAllFragment();
//
//       return movieAllFragment;
//   }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        myView = inflater.inflate(R.layout.activity_movie_all, container, false);
//
//        listview = (ListView) myView.findViewById(R.id.movie_list_view);
//        mSocket.connect();
//        mSocket.on("data", getData);
//
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.content_frame, new SignupFragment())
//                        .addToBackStack(null)
//                        .commit();
//                MovieAllFragment movieAllFragment = new MovieAllFragment();
//                Bundle i = new Bundle();
//
//                try {
//                    i.putString("name", jsonarray.getJSONObject(position).getString("name"));
//                    i.putString("duration", String.valueOf(jsonarray.getJSONObject(position).getInt("duration")));
//                    i.putString("director", jsonarray.getJSONObject(position).getString("director"));
//                    i.putString("actornactress", jsonarray.getJSONObject(position).getString("actornactress"));
//                    i.putString("nation", jsonarray.getJSONObject(position).getString("nation"));
//                    i.putString("language", jsonarray.getJSONObject(position).getString("language"));
//                    i.putString("category", jsonarray.getJSONObject(position).getString("category"));
//                    i.putString("startday", jsonarray.getJSONObject(position).getString("startday"));
//                    i.putString("format", jsonarray.getJSONObject(position).getString("format"));
//                    i.putString("imdb", String.valueOf(jsonarray.getJSONObject(position).getDouble("imdb")));
//                    i.putString("urltrailer", jsonarray.getJSONObject(position).getString("urltrailer"));
//                    i.putString("content", jsonarray.getJSONObject(position).getString("content"));
//                    i.putString("poster", "http://192.168.0.32:3000" + jsonarray.getJSONObject(position).getString("image"));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                movieAllFragment.setArguments(i);
//            }
//        });
//        return myView;
//    }
//
//    private void setMoviesList() {
//        adapter = new MovieAdapter(this, R.layout.movie_item, arrayList);
//        listview.setAdapter(adapter);
//    }
//
//}