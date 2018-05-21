package com.example.jingjing.blog9;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button button_enter;
    EditText editText_title;
    EditText editText_article;
    Context myContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void addBlog(){
        String name="Ginger";
        String title=editText_title.getText().toString().trim();
        String article=editText_article.getText().toString().trim();
        String like="0";

        if (!TextUtils.isEmpty(title)) {
//Create a list
            Map<String, Object> entryToPush = new HashMap<>();
            entryToPush.put("name", name);
            entryToPush.put("title", title);
            entryToPush.put("article", article);
            entryToPush.put("like", like);
// Add a new document with a generated ID

            db.collection("Blog_test1")
                    .add(entryToPush)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            String debugMsg = "DocumentSnapshot added with ID: " + documentReference.getId();
                            Toast.makeText(myContext,debugMsg, Toast.LENGTH_LONG );
                            Log.d("ginger", debugMsg);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String debugMsg = "Error adding document" + e.toString();
                            Toast.makeText(myContext,debugMsg, Toast.LENGTH_LONG );
                            Log.w("ginger", debugMsg);
                        }
                    });

        } else {
            Toast.makeText(this, "You should eat shit", Toast.LENGTH_LONG).show();
        }
    }
}


