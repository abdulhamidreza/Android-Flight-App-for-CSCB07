package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import cs.b07.cscb07courseproject.fragments.ClientProfileFragment;
import cs.b07.cscb07courseproject.fragments.EditClientFragment;
import cs.b07.cscb07courseproject.fragments.ItineraryBookFragment;
import cs.b07.cscb07courseproject.fragments.ItineraryListFragment;
import cs.b07.cscb07courseproject.fragments.MainClientFragment;
import cs.b07.cscb07courseproject.users.Client;

public class ClientActivity extends AppCompatActivity {

    public static Client client;
    private static EditText originET, destinationET, dateET, clientPassword, clientFirst, clientLast, clientAddress, clientCreditCard, clientCreditExpiry;

    public static final String originKey = "originKey";
    public static final String destinationKey = "destinationKey";
    public static final String dateKey = "dateKey";
    public static final String isDirectKey = "isDirectKey";
    public static final String clientKey = "clientKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Intent intent = getIntent();

        client = (Client) intent.getSerializableExtra(LogInActivity.userKey);

        /*originET = (EditText) findViewById(R.id.);
        destinationET = (EditText) findViewById(R.id.);
        dateET = (EditText) findViewById(R.id.); */

        Fragment frag = new MainClientFragment();
        setFragment(frag);
    }


    public void viewProfile(View view) {
        Bundle bundle = new Bundle();
        Fragment frag = new ClientProfileFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }

    public void editProfile(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(clientKey, client);

        Fragment frag = new EditClientFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }

    public void findFlight(View view) {
        /*Bundle bundle = new Bundle();
        bundle.putString(originKey, originET.getText().toString());
        bundle.putString(destinationKey,destinationET.getText().toString());
        bundle.putString(dateKey, dateET.getText().toString());
        bundle.putBoolean(isDirectKey, true);
        Fragment frag = new FlightListFragment();
        frag.setArguments(bundle);
        setFragment(frag);*/
    }

    public void findItinerary(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(originKey, originET.getText().toString());
        bundle.putString(destinationKey,destinationET.getText().toString());
        bundle.putString(dateKey, dateET.getText().toString());
        bundle.putBoolean(isDirectKey, false);

        Fragment frag = new ItineraryListFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }

    public void updateProfile(View view) {
    }

    public void bookItinerary(View view) {
    }



    public void setFragment (Fragment fragment){
        // changes the fragment
        android.support.v4.app.FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }


}


