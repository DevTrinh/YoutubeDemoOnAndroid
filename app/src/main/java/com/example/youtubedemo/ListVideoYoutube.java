package com.example.youtubedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.youtubedemo.adapter.AdapterVideo;
import com.example.youtubedemo.interfacee.InterfaceClickWithPosition;
import com.example.youtubedemo.interfacee.InterfaceValueDefault;
import com.example.youtubedemo.item.ItemVideoYoutube;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListVideoYoutube extends AppCompatActivity {

    RecyclerView rvListVideo;
    public static AdapterVideo adapterVideo;
    public static ArrayList<ItemVideoYoutube> listItemVideo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video_youtube);

        mapping();
        setListVideo();

        adapterVideo = new AdapterVideo(listItemVideo, new InterfaceClickWithPosition() {
            @Override
            public void onClickWithPosition(int position) {
                Intent intent = new Intent(ListVideoYoutube.this, PlayVideo.class);
                intent.putExtra("ValueIdVideo", listItemVideo.get(position).getId());
                startActivity(intent);
                Toast.makeText(ListVideoYoutube.this, listItemVideo.get(position).getTitleVideo()+"", Toast.LENGTH_SHORT).show();
            }
        });

        rvListVideo.setAdapter(adapterVideo);
        getJsonYoutube();
        Toast.makeText(this, listItemVideo.size()+"", Toast.LENGTH_SHORT).show();

    }

    public void setListVideo(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListVideo.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvListVideo.addItemDecoration(itemDecoration);
    }


    private void getJsonYoutube() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                InterfaceValueDefault.URL_JSON_API, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            String titleVideo = "";
                            String urlThumbnail = "";
                            String idVideo = "";
                            String titleChanel= "";
                            for (int i = 0; i < jsonItems.length(); i++) {
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                titleVideo = jsonSnippet.getString("title");
                                titleChanel = jsonSnippet.getString("videoOwnerChannelTitle");
                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                                urlThumbnail = jsonMedium.getString("url");
//                                RESOURCE ID
                                JSONObject jsonResourceId = jsonSnippet.getJSONObject("resourceId");
                                idVideo = (String) jsonResourceId.get("videoId");
//                                 GET DATA
                                listItemVideo.add(new ItemVideoYoutube(titleVideo, urlThumbnail, idVideo ,titleChanel));
//                                Toast.makeText(ListVideoYoutube.this, idVideo+"",
//                                        Toast.LENGTH_SHORT).show();
                            }
                            adapterVideo.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void mapping(){
        rvListVideo = findViewById(R.id.rv_list_video);
    }
}