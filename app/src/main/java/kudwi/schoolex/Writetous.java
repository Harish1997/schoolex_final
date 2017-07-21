package kudwi.schoolex;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by harishananth on 20/06/17.
 */

public class Writetous extends AppCompatActivity {
    String username,useremail;
    String message;
    Button send;
    EditText input;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writetous);
        Bundle extras=getIntent().getExtras();
        if(extras!=null) {
            username = extras.getString("name");
            useremail = extras.getString("email");
        }


        input=(EditText)findViewById(R.id.message);
        input.setSelection(0);
        send=(Button)findViewById(R.id.sendbutton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message=input.getText().toString();

                sendEmail(username,"kudwischoolexuser@gmail.com",message,useremail);
            }
        });
    }

    private void sendEmail(String uname, String uemail, String text, String usermail) {
        //Getting content for email
        String email =uemail;
        String subject = "SchooleX user message from "+uname;
        String message = "SchooleX user with email\t"+usermail+"\n\n"+text;
        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

}
