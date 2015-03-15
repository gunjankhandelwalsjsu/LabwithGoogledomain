package com.example.rajeshkhandelwal.labwithgoogledomain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;




public class Circles extends Fragment  {
    TextView text;




        private static final String TAG = "ListConnectedPeople";

        private static final String STATE_RESOLVING_ERROR = "resolving_error";


        private static final int REQUEST_CODE_SIGN_IN = 1;
        private static final int REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES = 2;

        private ArrayAdapter mListAdapter;
        private ListView mPersonListView;
        private ArrayList<String> mListItems;
        private GoogleApiClient mGoogleApiClient;
        private boolean mResolvingError;






        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

       /*private static PlusDomains authenticate() throws GeneralSecurityException, IOException {
        System.out.println(String.format("Authenticate the domain for %s", USER_EMAIL));

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        // Setting the sub field with USER_EMAIL allows you to make API calls using the special keyword
        // "me" in place of a user id for that user.
        GoogleCredential credential = new GoogleCredential.setAccessToken();
        Plus plus = new Plus.builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("Google-PlusSample/1.0")
                .build();

        // Create and return the authorized API client
        PlusDomains service = new PlusDomains.Builder(httpTransport, jsonFactory, credential).build();
        return service;
    }


    private static Map<String,String> getExistingCircles() throws IOException {
        Map<String,String> circles = new HashMap<String,String>();
                  PlusDomains plus = null;
                Person mePerson = plusDomains.people().get("me").execute();

                // Get all my Circles
        PlusDomains.Circles.List circleList = plus.circles().list("me");
        CircleFeed circleFeed = circleList.execute();
        while (circleFeed.getItems() != null && !circleFeed.getItems().isEmpty()) {
            for (Circle circle : circleFeed.getItems()) {
                // Remember this Circle
                circles.put(circle.getDisplayName(), circle.getId());
            }
            String nextPageToken = circleFeed.getNextPageToken();
            if (nextPageToken == null) {
                break;
            }
            circleList.setPageToken(nextPageToken);
            circleFeed = circleList.execute();
        }
        return circles;
    }

    /**
     * Get the IDs of the people in a given Circle.
     * @param circleId The ID of the Circle to get the members of.
     * @return List of Person IDs.
     * @throws IOException
     */
  /*  private static Set<String> getCircleUsers(String circleId) throws IOException {
        Set<String> users = new HashSet<String>();
        // Get the member list for this Circle
        Plus.People.ListByCircle peopleList = plus.people().listByCircle(circleId);
        PeopleFeed peopleFeed = peopleList.execute();
        while (peopleFeed.getItems() != null && !peopleFeed.getItems().isEmpty()) {
            for (Person person : peopleFeed.getItems()) {
                users.add(person.getId());
            }
            String nextPageToken = peopleFeed.getNextPageToken();
            if (nextPageToken == null) {
                break;
            }
            peopleList.setPageToken(nextPageToken);
            peopleFeed = peopleList.execute();
        }
        return users;
    }

*/

        @Override
        public void onStart() {
            super.onStart();
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
        }

        @Override
        public void onStop() {
            super.onStop();
        }



      public Circles()
      {
      }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_circles, container, false);
        mPersonListView = (ListView)layout.findViewById(R.id.person_list);

        mListItems = new ArrayList<String>();
        mListAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1 , mListItems);

        mPersonListView.setAdapter(mListAdapter);
        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
/*
        PlusDomains plusDomains = new PlusDomains.Builder(new NetHttpTransport(),new JacksonFactory(),new GoogleCredential().setAccessToken(token)).build();

        PlusDomains.Circles.List listCircles = null;
        try {
            listCircles = plusDomains.circles().list("me");
        } catch (IOException e) {
            e.printStackTrace();
        }
        listCircles.setMaxResults(5L);
        CircleFeed circleFeed = null;
        try {
            circleFeed = listCircles.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Circle> circles = circleFeed.getItems();

// Loop until no additional pages of results are available.
        while (circles != null) {
            for (Circle circle : circles) {
                System.out.println(circle.getDisplayName());
            }

            // When the next page token is null, there are no additional pages of
            // results. If this is the case, break.
            if (circleFeed.getNextPageToken() != null) {
                // Prepare the next page of results
                listCircles.setPageToken(circleFeed.getNextPageToken());

                // Execute and process the next page request
                try {
                    circleFeed = listCircles.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                circles = circleFeed.getItems();
            } else {
                circles = null;
            }
        }
*/
        return layout;
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


}

