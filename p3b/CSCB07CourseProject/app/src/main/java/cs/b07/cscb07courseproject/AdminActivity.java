package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cs.b07.cscb07courseproject.database.Database;
import cs.b07.cscb07courseproject.fragments.AddThroughFileFragment;
import cs.b07.cscb07courseproject.fragments.ClientListFragment;
import cs.b07.cscb07courseproject.fragments.FlightListFragment;
import cs.b07.cscb07courseproject.fragments.ItineraryListFragment;
import cs.b07.cscb07courseproject.fragments.MainAdminFragment;
import cs.b07.cscb07courseproject.fragments.ViewFlightFragment;
import cs.b07.cscb07courseproject.users.Admin;

/**
 * Controls the admin's activity.
 */
public class AdminActivity extends AppCompatActivity {

    public static Admin admin;
    public static Database db;

    public static final String originKey = "originKey";
    public static final String destinationKey = "destinationKey";
    public static final String dateKey = "dateKey";
    public static final String isDirectKey = "isDirectKey";
    public static final String clientKey = "clientKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent intent = getIntent();

        admin = (Admin) intent.getSerializableExtra(LogInActivity.userKey);
        db = (Database) intent.getSerializableExtra(LogInActivity.dataKey);

        Bundle bundle = new Bundle();
        Fragment frag = new MainAdminFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }
    /**
     * Changes the current view to the AddThroughFileFragment fragment.
     * @param view the current view
     */
    public void addClient(View view) {
        Bundle bundle = new Bundle();
        Fragment frag = new AddThroughFileFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }
    /**
     * Changes the current view to the ClientListFragment fragment.
     * @param view the current view
     */
    public void viewClients(View view) {
        Bundle bundle = new Bundle();
        Fragment frag = new ClientListFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }
    /**
     * Changes the current view to the AddThroughFileFragment fragment.
     * @param view the current view
     */
    public void addFlight(View view) {
        Bundle bundle = new Bundle();
        Fragment frag = new AddThroughFileFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }
    /**
     * Changes the current view to the """""""""""""""" fragment.
     * @param view the current view
     */
    public void searchFlight(View view) {
        Bundle bundle = MainAdminFragment.bundleFlightDetail();
        if (!bundle.isEmpty()) {
            bundle.putBoolean(isDirectKey, true);
            bundle.putBoolean(LogInActivity.isClientKey, false);

            Fragment frag = new FlightListFragment();
            frag.setArguments(bundle);
            setFragment(frag);
        }else{
            Toast.makeText(this,
                    getString(R.string.fill_out_details),
                    Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Changes the current view to the ItineraryListFragment fragment.
     * @param view the current view
     */
    public void searchItineraries(View view) {
        Bundle bundle = MainAdminFragment.bundleFlightDetail();
        if (!bundle.isEmpty()) {
            bundle.putBoolean(isDirectKey, false);
            bundle.putBoolean(LogInActivity.isClientKey, false);

            Fragment frag = new ItineraryListFragment();
            frag.setArguments(bundle);
            setFragment(frag);
        }else{
            Toast.makeText(this,
                    getString(R.string.fill_out_details),
                    Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Updates a flight information.
     */
    public void updateFlight(){
        if(ViewFlightFragment.updateFlightInfo()){
            Fragment frag = new MainAdminFragment();
            setFragment(frag);
        }else{
            Toast.makeText(this,
                    getString(R.string.msg_signup_success),
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Changes the current fragment to target fragment.
     * @param fragment the fragment being changed to
     */
    private void setFragment (Fragment fragment){
        // changes the fragment
        android.support.v4.app.FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

}
