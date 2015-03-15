package com.example.rajeshkhandelwal.labwithgoogledomain;

/**
 * Created by rajeshkhandelwal on 3/13/15.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import Tabs.SlidingTabLayout;


public class HomeActivity extends ActionBarActivity {
    ImageView imageProfile;
    private Button SignOutBtn, RevokeAccessbtn;

    TextView textViewName, textViewEmail, textViewGender, textViewBirthday;
    String textName, textEmail, textGender, textBirthday, userImageUrl,token;
    private Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    private LinearLayout llProfileLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setLogoDescription(getResources().getString(R.string.GooglePlus));
        toolbar.setLogo(R.drawable.download);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
   //     llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);
      //  SignOutBtn = (Button) findViewById(R.id.btn_sign_out);
       // RevokeAccessbtn = (Button) findViewById(R.id.btn_revoke_access);
        imageProfile = (ImageView) findViewById(R.id.imageView1);
        textViewName = (TextView) findViewById(R.id.textViewNameValue);
        textViewEmail = (TextView) findViewById(R.id.textViewEmailValue);
        textViewGender = (TextView) findViewById(R.id.textViewGenderValue);
        textViewBirthday = (TextView) findViewById(R.id.textViewBirthdayValue);
     //   SignOutBtn.setOnClickListener(this);
     //   RevokeAccessbtn.setOnClickListener(this);

        mPager = (ViewPager) findViewById(R.id.pager);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs.setDistributeEvenly(true);
        mTabs.setViewPager(mPager);

        /**
         * get user email using intent
         */

        Intent intent = getIntent();
        textEmail = intent.getStringExtra("email_id");
        System.out.println(textEmail);
        textViewEmail.setText(textEmail);

        /**
         * get user data from google account
         */

        try {
            System.out.println("On Home Page***"
                    + AbstractGetNameTask.GOOGLE_USER_DATA);
            JSONObject profileData = new JSONObject(
                    AbstractGetNameTask.GOOGLE_USER_DATA);

            if (profileData.has("picture")) {
                userImageUrl = profileData.getString("picture");
                new GetImageFromUrl().execute(userImageUrl);
            }
            if (profileData.has("name")) {
                textName = profileData.getString("name");
                textViewName.setText(textName);
            }
            if (profileData.has("gender")) {
                textGender = profileData.getString("gender");
                textViewGender.setText(textGender);
            }
            if (profileData.has("birthday")) {
                textBirthday = profileData.getString("birthday");
                textViewBirthday.setText(textBirthday);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_out:
                // Signout button clicked
                signOutFromGooglePlus();
                break;
            case R.id.btn_revoke_access:
                // Revoke access button clicked
                revokeGooglePlusAccess();
                break;
        }
    }
    private void signOutFromGooglePlus() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
            updateUI(false);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }


    /**
     * Revoking access from google
     */
    /*
    private void revokeGooglePlusAccess() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status arg0) {
                            Log.e("inOn Rsult", "User access revoked!");
                            mGoogleApiClient.connect();
                            updateUI(false);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }

                    });
        }
    }
*/

    /**
     * Updating the UI, showing/hiding buttons and profile layout
     */
    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            SignOutBtn.setVisibility(View.VISIBLE);
            RevokeAccessbtn.setVisibility(View.VISIBLE);
            llProfileLayout.setVisibility(View.VISIBLE);


        } else {
            SignOutBtn.setVisibility(View.GONE);
            RevokeAccessbtn.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.GONE);
        }
    }


    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            imageProfile.setImageBitmap(result);
        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
    class MyPagerAdapter extends FragmentPagerAdapter {
        String[] tabs;
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }


        @Override
        public Fragment getItem(int position) {
            Log.i("Im Here", "pos" + position);

            if (position == 0) // if the position is 0 we are returning the First tab
            {
                Profile profile = new Profile();

                return profile;
            } else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
            {

                Circles frag = new Circles();
                return frag;
            }

        }



        public CharSequence getPageTitle(int position)
        {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
