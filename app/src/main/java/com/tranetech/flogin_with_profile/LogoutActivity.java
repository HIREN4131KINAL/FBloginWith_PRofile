package com.tranetech.flogin_with_profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class LogoutActivity extends AppCompatActivity {

    private TextView btnLogout, tv_name, tv_gmail, tv_gender;
    private User user;
    private ProfilePictureView profileImage;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        user = PrefUtils.getCurrentUser(LogoutActivity.this);
        profileImage = (ProfilePictureView) findViewById(R.id.profileImage);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_gmail = (TextView) findViewById(R.id.tv_gmail);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        //  profileImage.setProfileId(String.valueOf(R.string.facebook_app_id));

        // fetching facebook's profile picture
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                URL imageURL = null;
                try {
                    imageURL = new URL("https://graph.facebook.com/715697225181548/picture?type=large");
                    //  imageURL = new URL("https://graph.facebook.com/868281133254730/picture?width=120&height=120");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //profileImage.setImageBitmap(bitmap);
                profileImage.setProfileId("715697225181548");
                tv_name.setText(user.name);
                tv_gmail.setText(user.email);
                tv_gender.setText(user.gender);
            }
        }.execute();


        btnLogout = (TextView) findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.clearCurrentUser(LogoutActivity.this);

                // We can logout from facebook by calling following method
                LoginManager.getInstance().logOut();
                Intent i = new Intent(LogoutActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}
