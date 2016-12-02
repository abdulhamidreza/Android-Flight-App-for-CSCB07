package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;

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

    public static String admin;
    public static Database db;

    private static String fileType;

    public static final String originKey = "originKey";
    public static final String destinationKey = "destinationKey";
    public static final String dateKey = "dateKey";
    public static final String isDirectKey = "isDirectKey";

    /**
     * Initializes the admin activity.
     * @param savedInstanceState the saved instance state of client
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent intent = getIntent();

        admin = intent.getStringExtra(LogInActivity.userKey);
        db = (Database) intent.getSerializableExtra(LogInActivity.dataKey);

        Bundle bundle = new Bundle();
        Fragment frag = new MainAdminFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }

    /**
     * Updates the database upon end of application.
     */
    protected  void onDestroy() {
        super.onDestroy();
        db.update();

    }
    /**
     * Changes the current view to the AddThroughFileFragment fragment.
     * @param view the current view
     */
    public void addClient(View view) {
        Bundle bundle = new Bundle();
        Fragment frag = new AddThroughFileFragment();
        fileType = "Client";
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
        fileType = "Flight";
        frag.setArguments(bundle);
        setFragment(frag);
    }
    /**
     * Searches for a flight with given information.
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
        System.out.println(db.getFlights());
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
            db.updateFlight();
            Toast.makeText(this,
                    getString(R.string.msg_update_flight_success),
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,
                    getString(R.string.msg_update_flight_unsuccessful),
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Loads a file.
     * @param view the current view
     */
    public void loadFile(View view){
       String fileName = AddThroughFileFragment.loadFileClick();
        try {
            switch (fileType) {
                case "Flight":
                    db.readFlightTxt(Environment.getExternalStorageDirectory().toString(), fileName);
                    Toast.makeText(this,
                            getString(R.string.success_upload_file),
                            Toast.LENGTH_LONG).show();
                    break;
                case "Client":
                    db.readInClientTxt(Environment.getExternalStorageDirectory().toString(), fileName);
                    Toast.makeText(this,
                            getString(R.string.success_upload_file),
                            Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(this,
                            getString(R.string.wrong_file_type),
                            Toast.LENGTH_LONG).show();
                    break;
            }
            Fragment frag = new MainAdminFragment();
            setFragment(frag);

        }catch(IOException ex){
            Toast.makeText(this,
                    getString(R.string.error_finding_file),
                    Toast.LENGTH_LONG).show();
        }catch(ParseException ex){
            Toast.makeText(this,
                    getString(R.string.parse_error),
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Logs the user out and returns them to the login fragment.
     * @param view the current view
     */
    public void log_out(View view){
        finish();
    }

    /**
     * Switches the admin to the home screen
     * @param view the current view
     */
    public void home(View view){
        Fragment frag = new MainAdminFragment();
        setFragment(frag);
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
