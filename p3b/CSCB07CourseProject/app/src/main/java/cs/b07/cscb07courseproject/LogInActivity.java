package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import cs.b07.cscb07courseproject.fragments.LogInFragment;
import cs.b07.cscb07courseproject.fragments.CreateUserFragment;
import cs.b07.cscb07courseproject.users.Admin;
import cs.b07.cscb07courseproject.users.Client;
import cs.b07.cscb07courseproject.users.User;
import cs.b07.cscb07courseproject.database.Database;

//import static android.content.Intent.EXTRA_USER;

public class LogInActivity extends AppCompatActivity {

    // Keys for transferring data to other activities or fragments
    public static final String userKey = "user";
    public static final String dataKey = "dataKey";
    public static final String isClientKey = "isClient";

    private static Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        File thisContext = this.getApplicationContext().getFilesDir();
        String appPath = thisContext.getAbsolutePath();
        db = new Database(appPath + "/client.txt", appPath + "./admin.txt", appPath + "./flight.txt");


        setFragment(new LogInFragment());

    }

    /**
     * Method called when @R.id.loginBtn is clicked.
     * @param view current view.
     */
    public void login(View view) {
        // will need to edit this when back end is done to actually login in people
        EditText emailET = (EditText) findViewById(R.id.emailET);
        String userEmail = emailET.getText().toString();
        EditText passwordET = (EditText) findViewById(R.id.passwordET);
        String userPassword = passwordET.getText().toString();
        User user = null;
        boolean clientFound = false;
        boolean adminFound = false;
        for (Client client: db.getClients()){
            if (client.login(userEmail, userPassword)){
                user = client;
                clientFound = true;
                break;
            }
        }
        for (Admin admin: db.getAdmins()){
            if (admin.login(userEmail, userPassword)){
                user = admin;
                adminFound = true;
                break;
            }
        }

        Intent intent = null;

        if (clientFound) {
            intent = new Intent(this, ClientActivity.class);
        } else if (adminFound){
            intent = new Intent(this, AdminActivity.class);
        }
        if (user != null) {
            intent.putExtra(userKey, user.getEmail());
            intent.putExtra(dataKey, db);
            startActivity(intent);

            // Creates a pop up message in app
            Toast.makeText(this,
                    getString(R.string.msg_log_in_success),
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,
                    getString(R.string.msg_log_in_error),
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method called when @R.id.createAdminBtn is clicked.
     * @param view current view.
     */
    public void createAdmin(View view) {
        // Use bundle to send data to Fragments just like intent for activities
        Bundle bundle = new Bundle();
        bundle.putBoolean(isClientKey, false);
        Fragment frag = new CreateUserFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }

    /**
     * Method called when @R.id.createClientAdminBtn is clicked.
     * @param view current view.
     */
    public void createClient(View view) {
        // Use bundle to send data to Fragments just like intent for activities
        Bundle bundle = new Bundle();
        bundle.putBoolean(isClientKey, true);
        Fragment frag = new CreateUserFragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }

    /**
     * Method called when @R.id.createUserBtn is clicked.
     * @param view current view.
     */
    public void createUser(View view) {
        try {
            // If user is successfully made then create a new user and
            // change activities to the correct user activity
            User newUser = CreateUserFragment.createUser();
            Intent intent;
            if (CreateUserFragment.getIsClient()){
                intent = new Intent(this, ClientActivity.class);
                db.AddNewClient((Client) newUser);
            }else{
                intent = new Intent(this, AdminActivity.class);
                db.AddNewAdmin((Admin) newUser);
            }
            intent.putExtra(userKey, newUser.getEmail());
            startActivity(intent);

            Toast.makeText(this,
                    getString(R.string.msg_signup_success),
                    Toast.LENGTH_SHORT).show();
        }catch (ParseException e) {
            Toast.makeText(this,
                    getString(R.string.msg_signup_error),
                    Toast.LENGTH_SHORT).show();
        }catch (NullPointerException ex){
            Toast.makeText(this,
                    getString(R.string.error_field_required),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Changes the fragment.
     * @param fragment new fragment to change to.
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
