package kudwi.schoolex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by harishananth on 30/06/17.
 */

public class Configprofile extends AppCompatActivity  implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{

    CircleImageView profimg;
    String uname,uemail,url;
    LoginButton loginButton;
    CallbackManager callbackManager;
    Button done;
    private LinearLayout prof_section;
    private SignInButton signin;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN=1;
    private GoogleApiClient googleapiclient;
    private FirebaseAuth mAuth;
    private static final String TAG="LOGIN_ACTIVITY";
    private FirebaseAuth.AuthStateListener mAuthListener;
    public Intent signInIntent;
    private PrefManager prefManager;
    EditText email,phone;
    Spinner spinner;
    String item;
    String name,emailst,phonest;
    SharedPreferences sharedPref;


    public CallbackManager mCallbackmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = getSharedPreferences("userinfo", MODE_PRIVATE);

        {
            FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.configprofile);
            mAuth = FirebaseAuth.getInstance();
profimg=(CircleImageView)findViewById(R.id.profimg);
            spinner=(Spinner)findViewById(R.id.cityspinner);
            email=(EditText)findViewById(R.id.emailinput);
            phone=(EditText)findViewById(R.id.phoneinput);
            done=(Button)findViewById(R.id.configdone);

            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (firebaseAuth.getCurrentUser() != null) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                         name = user.getDisplayName();
                        Toast.makeText(Configprofile.this,name,Toast.LENGTH_SHORT).show();
                         emailst = user.getEmail();

                    }


                }
            };

            List<String> cities=new ArrayList<>();
            cities.add("Pick a city");
            cities.add("Chennai");
            cities.add("Mumbai -Coming soon");
            cities.add("Delhi -Coming soon");
            cities.add("Banglore -Coming soon");
            cities.add("Kolkata -Coming soon");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cities);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                    item = spinner.getItemAtPosition(i).toString();

                    // Showing selected spinner item
                    //Toast.makeText(Citychooser.this, "Selected: " + item, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    emailst=email.getText().toString();
                    phonest=phone.getText().toString();


                    Savedata(name,emailst,url,phonest,item);

                }
            });

            signin = (SignInButton) findViewById(R.id.glogin);

            signin.setOnClickListener(this);

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(Configprofile.this.getResources().getString(R.string.server_client_id))
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext()).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                @Override
                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    Toast.makeText(Configprofile.this, "Connection failed", Toast.LENGTH_SHORT).show();

                }
            }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "kudwi.schoolex",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/
            loginButton = (LoginButton) findViewById(R.id.fblogin);
            callbackManager = CallbackManager.Factory.create();
            loginButton.setReadPermissions("email", "public_profile");
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    handlefacebookaccesstoken(loginResult.getAccessToken());


                }

                @Override
                public void onCancel() {


                }

                @Override
                public void onError(FacebookException error) {

                }
            });


        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        callbackManager.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RC_SIGN_IN)
        {
            //Toast.makeText(Login.this,"Signin success",Toast.LENGTH_SHORT).show();
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess())
            {
                Toast.makeText(Configprofile.this,"Signin success", Toast.LENGTH_SHORT).show();
                GoogleSignInAccount account=result.getSignInAccount();
                firebaseAuthwithGoogle(account);
            }
        }
    }
    private void handlefacebookaccesstoken(AccessToken token)
    {
        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("","signinwithcred:on complete:"+task.isSuccessful());
                        if(!task.isSuccessful())
                        {
                            Log.v("","signinwithcred",task.getException());
                            Toast.makeText(Configprofile.this,"Facebook login failed", Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

    private void firebaseAuthwithGoogle(GoogleSignInAccount account) {

        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = mAuth.getCurrentUser();

                            assert user != null;
                            name= user.getDisplayName();
                             emailst= user.getEmail();
                            if(user.getPhotoUrl()!=null)
                                url = user.getPhotoUrl().toString();


                            //Toast.makeText(Login.this,name+" "+email+" "+img_url+" "+user.getPhoneNumber(),Toast.LENGTH_LONG).show();



                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Configprofile.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.glogin:
                signin();
                break;


        }

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }

    private void signin()
    {

        signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);


    }

    private void signout()
    {


        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });
        // UpdateUI(false);

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void Savedata(String name,String email,String url,String phone,String city)
    {
        sharedPref=getSharedPreferences("userinfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putString("name",name);
        editor.putString("email",email);
        editor.putString("url",url);
        editor.putString("phone",phone);
        editor.putString("city",city);
        editor.apply();
        launchHomeScreen();
    }


    private void launchHomeScreen() {
        Intent intent=new Intent(Configprofile.this,MainActivity.class);
        intent.putExtra("name",uname);
        intent.putExtra("email",uemail);
        intent.putExtra("url",url);
        intent.putExtra("city",item);
        startActivity(intent);
        finish();
    }

}
