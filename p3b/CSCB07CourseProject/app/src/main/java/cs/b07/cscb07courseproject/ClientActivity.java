package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs.b07.cscb07courseproject.fragments.MainClientFragment;
import cs.b07.cscb07courseproject.fragments.PlaceHolderFragment;
import cs.b07.cscb07courseproject.users.Client;

public class ClientActivity extends AppCompatActivity {

    public static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Intent intent = getIntent();

        client = (Client) intent.getSerializableExtra(LogInActivity.userKey);

        Fragment frag = new MainClientFragment();
        setFragment(frag);
    }


    public void viewProfile(View view) {

    }

    public void editProfile(View view) {

    }

    public void findFlight(View view) {

    }

    public void findItinerary(View view) {

    }


    private void setFragment (Fragment fragment){
        // changes the fragment
        android.support.v4.app.FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }


}


