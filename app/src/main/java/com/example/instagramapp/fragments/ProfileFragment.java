package com.example.instagramapp.fragments;

import android.util.Log;
import android.widget.Toast;

import com.example.instagramapp.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostFragment {

    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.key_user);
        query.whereEqualTo(Post.key_user, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.key_created);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(tag, "not null!", e);
                    return;
                }
                for (Post post : posts) { //added username here
                    Log.i(tag, "post: " + post.getDescription() + ", by " + post.getUser().getUsername());
                    Toast.makeText(getContext(), "post here", Toast.LENGTH_SHORT).show();
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
