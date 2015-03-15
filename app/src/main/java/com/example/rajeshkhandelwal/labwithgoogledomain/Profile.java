package com.example.rajeshkhandelwal.labwithgoogledomain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Profile extends Fragment {
    TextView textView;

    public static Profile newInstance(int position) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        Log.i("pos","position at"+position);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //Log.i("pos","position at"+position);


        View layout= inflater.inflate(R.layout.fragment_profile, container, false);

        Bundle bundle=getArguments();
        if(bundle!=null)
        {
         textView.setText("the page is"+bundle.getInt("position"));
        }
        return layout;
    }

}
