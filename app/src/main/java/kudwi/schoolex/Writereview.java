package kudwi.schoolex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by harishananth on 28/06/17.
 */

public class Writereview extends AppCompatActivity {

Float val;
    RatingBar reviewrate;
    private DatabaseReference root;
String reviewstring,rating;
    EditText review;
    Button submit;
    String temp_key1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writereview);
        root= FirebaseDatabase.getInstance().getReference().child(schoolact.school+" review");
        review=(EditText)findViewById(R.id.reviewbox);
        submit=(Button)findViewById(R.id.submitreview);
        reviewrate=(RatingBar)findViewById(R.id.reviewrate);
        review.setSelection(0);
        setTitle("Review\t"+schoolact.school);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
reviewstring=review.getText().toString();
                rating= String.valueOf(reviewrate.getRating());
                Map<String, Object> map3 = new HashMap<String, Object>();
                temp_key1 = root.push().getKey();
                root.updateChildren(map3);
                DatabaseReference message_root2 = root.child(temp_key1);
                Map<String, Object> map4 = new HashMap<String, Object>();
                map4.put("name", schoolact.name);
                map4.put("rating", rating);
                map4.put("review", reviewstring);
                message_root2.updateChildren(map4);
            }
        });
    }
}
