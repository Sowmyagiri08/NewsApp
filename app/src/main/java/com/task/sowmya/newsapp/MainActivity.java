package com.task.sowmya.newsapp;

import android.app.DownloadManager;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.task.sowmya.newsapp.R.id.newsItems;

public class MainActivity extends AppCompatActivity {
    private List<newsItem>newsFeed= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue=Volley.newRequestQueue(this);
        String requestBody="";
      JsonObjectRequest myReq=new JsonObjectRequest(Request.Method.GET,"https://newsapi.org/v1/articles?source=cnn&apiKey=1e936053fccd414a8493b0c167e8d17f", requestBody
              , new Response.Listener<JSONObject>() {
          @Override
          public void onResponse(JSONObject response) {
              try {
                  JSONArray newsItems = response.getJSONArray("articles");
                  for (int i = 0; i < newsItems.length(); i++) {
                      JSONObject temp = newsItems.getJSONObject(i);
                      String author = temp.getString("author");
                      String title = temp.getString("title");
                      String desc = temp.getString("desc");
                      String url = temp.getString("url");
                      String urlToImage = temp.getString("urlToImage");
                      String publishedAt = temp.getString("publishedAt");
newsFeed.add(new newsItem(author,title,desc,url,urlToImage,publishedAt));

                  }
              } catch (JSONException e) {
                  Log.i("myTag",e.toString());
              }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
Log.i("myTag",error.toString());
          }
      });
        queue.add(myReq);
        ArrayAdapter<newsItem> adapter=new customAdapter();
        ListView newsItems=(ListView) findViewById(R.id.newsItems);
        newsItems.setAdapter(adapter);
        AdapterView.OnItemClickListener newsclick=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Toast.makeText(MainActivity.this,"My List Items",Toast.LENGTH_SHORT) ;
            }
        };
    }
    private class customAdapter extends ArrayAdapter<newsItem>{
        public customAdapter() {
            super(MainActivity.this, R.layout.my_layout,newsFeed);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null) {
                convertView = getLayoutInflater().inflate(R.layout.my_layout,parent,false);
            }
            newsItem myCurrentItem=newsFeed.get(position);
            ImageView newsImage= (ImageView) convertView.findViewById(R.id.leftIcon);
            TextView heading= (TextView) convertView.findViewById(R.id.heading);
            TextView desc= (TextView) convertView.findViewById(R.id.desc);
            heading.setText(myCurrentItem.getNewsTitle());
            desc.setText(myCurrentItem.getNewsDesc());
            newsImage.setImageResource(R.mipmap.ic_launcher);

            return convertView;
        }
    }
}
