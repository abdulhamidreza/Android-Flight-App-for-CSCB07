package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import cs.b07.cscb07courseproject.fragments.Log_In_Fragment;
import cs.b07.cscb07courseproject.fragments.Create_User_Fragment;
import cs.b07.cscb07courseproject.users.Admin;
import cs.b07.cscb07courseproject.users.User;

public class LogInActivity extends AppCompatActivity {

    public static final String userKey = "user";
    public static final String isClientKey = "isClient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setFragment(new Log_In_Fragment());
    }

    public void login(View view) {
        EditText emailET = (EditText) findViewById(R.id.emailET);
        String userEmail = emailET.getText().toString();
        EditText passwordET = (EditText) findViewById(R.id.passwordET);
        String userPassword = passwordET.getText().toString();
        User user = new Admin(userEmail, userPassword);
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra(userKey, user);
        startActivity(intent);
    }

    public void createAdmin(View view) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(isClientKey, false);
        Fragment frag = new Create_User_Fragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }

    public void createClient(View view) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(isClientKey, true);
        Fragment frag = new Create_User_Fragment();
        frag.setArguments(bundle);
        setFragment(frag);
    }

    public void createUser(View view) {
        User newAdmin = Create_User_Fragment.createAdmin();
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra(userKey, newAdmin);
        startActivity(intent);
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
