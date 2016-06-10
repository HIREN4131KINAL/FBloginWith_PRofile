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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        user = PrefUtils.getCurrentUser(LogoutActivity.this);
        profileImage = (ProfilePictureView) findViewById(R.id.profileImage);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_gmail = (TextView) findViewById(R.id.tv_gmail);
        tv_gender = (TextView) findViewById(R.id.tv_gender);

        tv_name.setText(user.name);
        tv_gmail.setText(user.email);
        tv_gender.setText(user.gender);

        profileImage.setProfileId("715697225181548");
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
