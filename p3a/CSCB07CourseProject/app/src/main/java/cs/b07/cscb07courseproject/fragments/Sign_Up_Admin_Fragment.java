package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.users.Admin;
import cs.b07.cscb07courseproject.users.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sign_Up_Admin_Fragment extends Fragment {

    private static EditText email,password;
    View rootView;
    public Sign_Up_Admin_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sign_up_admin, container, false);
        email = (EditText) rootView.findViewById(R.id.emailET);
        password = (EditText) rootView.findViewById(R.id.passwordET);
        return rootView;
    }

    public static User createAdmin(){
        return new Admin(email.getText().toString(),
                password.getText().toString());
    }

}
