package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs.b07.cscb07courseproject.fragments.MainAdminFragment;
import cs.b07.cscb07courseproject.fragments.PlaceHolderFragment;
import cs.b07.cscb07courseproject.users.Admin;

public class AdminActivity extends AppCompatActivity {

    public static Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent intent = getIntent();

        admin = (Admin) intent.getSerializableExtra(LogInActivity.userKey);

        Bundle bundle = new Bundle();
        Fragment frag = new MainAdminFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }

    public void addClient(View view) {

    }
    public void viewClients(View view) {

    }
    public void addFlight(View view) {

    }
    public void searchFlight(View view) {

    }
    public void searchItineraries(View view) {

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
