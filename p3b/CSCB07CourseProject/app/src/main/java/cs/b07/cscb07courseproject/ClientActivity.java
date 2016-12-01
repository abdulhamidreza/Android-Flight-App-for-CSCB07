package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import cs.b07.cscb07courseproject.fragments.ClientProfileFragment;
import cs.b07.cscb07courseproject.fragments.EditClientFragment;
import cs.b07.cscb07courseproject.fragments.FlightListFragment;
import cs.b07.cscb07courseproject.fragments.ItineraryBookFragment;
import cs.b07.cscb07courseproject.fragments.ItineraryListFragment;
import cs.b07.cscb07courseproject.fragments.MainClientFragment;
import cs.b07.cscb07courseproject.fragments.ViewFlightFragment;
import cs.b07.cscb07courseproject.users.Client;

public class ClientActivity extends AppCompatActivity {

    public static Client client;

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
        // maybe erase this and just call client variable from this class in editClientFragment
        bundle.putSerializable(clientKey, client);

        Fragment frag = new EditClientFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }

    public void findFlight(View view) {
        Bundle bundle = MainClientFragment.bundleFlightDetail();
        if (!bundle.isEmpty()) {
            bundle.putBoolean(isDirectKey, true);
            bundle.putBoolean(LogInActivity.isClientKey, true);

            Fragment frag = new FlightListFragment();
            frag.setArguments(bundle);
            setFragment(frag);
        }else{
            Toast.makeText(this,
                    getString(R.string.fill_out_details),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void findItinerary(View view) {
        Bundle bundle = MainClientFragment.bundleFlightDetail();
        if (!bundle.isEmpty()) {
            bundle.putBoolean(isDirectKey, false);
            bundle.putBoolean(LogInActivity.isClientKey, true);

            Fragment frag = new ItineraryListFragment();
            frag.setArguments(bundle);
            setFragment(frag);
        }else{
            Toast.makeText(this,
                    getString(R.string.fill_out_details),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void updateProfile(View view) {
        if (EditClientFragment.updateProfile()){
            Fragment frag = new MainClientFragment();
            setFragment(frag);
            Toast.makeText(this,
                    getString(R.string.msg_profile_update_success),
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,
                    getString(R.string.error_field_required),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void bookItinerary(View view) {
        if(ItineraryBookFragment.bookItinerary()) {
            Fragment frag = new MainClientFragment();
            setFragment(frag);
            Toast.makeText(this,
                    getString(R.string.book_success),
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,
                    getString(R.string.book_error),
                    Toast.LENGTH_LONG).show();
        }
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


