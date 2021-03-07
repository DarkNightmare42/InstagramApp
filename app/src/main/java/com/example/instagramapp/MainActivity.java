package com.example.instagramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String tag = "MainActivity";
    private EditText etDescription;
    private Button selectBtn;
    private Button postBtn;
    private ImageView postImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDescription = findViewById(R.id.description);
        selectBtn = findViewById(R.id.uploadBtn);
        postBtn = findViewById(R.id.postBtn);
        postImage = findViewById(R.id.postImage);

        queryPosts();
    }

    private void queryPosts(){
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.key_user);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e(tag, "not null!", e);
                    return;
                }
                for(Post post : posts){
                    Log.i(tag, "post: " + post.getDescription() + ", by " + post.getUser().getUsername());
                }
            }
        });
    }
}