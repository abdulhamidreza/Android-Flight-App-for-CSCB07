package cs.b07.cscb07courseproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cs.b07.cscb07courseproject.fragments.PlaceHolderFragment;
import cs.b07.cscb07courseproject.users.Admin;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent intent = getIntent();

        Admin admin = (Admin) intent.getSerializableExtra(LogInActivity.userKey);

        Bundle bundle = new Bundle();
        bundle.putString("placeholder", "Admin Activity");
        Fragment frag = new PlaceHolderFragment();
        frag.setArguments(bundle);
        setFragment(frag);
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
