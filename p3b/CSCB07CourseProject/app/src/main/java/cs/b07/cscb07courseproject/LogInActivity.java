package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.text.ParseException;

import cs.b07.cscb07courseproject.fragments.LogInFragment;
import cs.b07.cscb07courseproject.fragments.CreateUserFragment;
import cs.b07.cscb07courseproject.users.Admin;
import cs.b07.cscb07courseproject.users.User;

//import static android.content.Intent.EXTRA_USER;

public class LogInActivity extends AppCompatActivity {

    // Keys for transferring data to other activities or fragments
    public static final String userKey = "user";
    public static final String isClientKey = "isClient";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        User user = new Admin(userEmail, userPassword);
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra(userKey, user);
        startActivity(intent);

        // Creates a pop up message in app
        Toast.makeText(this,
                getString(R.string.msg_log_in_success),
                Toast.LENGTH_LONG).show();
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
            }else{
                intent = new Intent(this, AdminActivity.class);
            }
            intent.putExtra(userKey, newUser);
            startActivity(intent);

            Toast.makeText(this,
                    getString(R.string.msg_signup_success),
                    Toast.LENGTH_LONG).show();
        }catch (ParseException e) {
            Toast.makeText(this,
                    getString(R.string.msg_signup_error),
                    Toast.LENGTH_LONG).show();

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
