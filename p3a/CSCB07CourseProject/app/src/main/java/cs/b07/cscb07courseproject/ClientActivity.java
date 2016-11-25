package cs.b07.cscb07courseproject;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cs.b07.cscb07courseproject.fragments.Log_In_Fragment;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        setFragment(new Log_In_Fragment());
    }



    public void signUpAdmin(View view){
        Log_In_Fragment.signUpAdmin(view);
        System.out.println("In Client Activity");
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


