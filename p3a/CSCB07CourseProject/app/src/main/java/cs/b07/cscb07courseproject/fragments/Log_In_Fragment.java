package cs.b07.cscb07courseproject.fragments;

import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cs.b07.cscb07courseproject.LogInActivity;
import cs.b07.cscb07courseproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Log_In_Fragment extends Fragment {

    private View rootView;

    public Log_In_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_log_in, container, false);


        return rootView;
    }

    public static void logIn (View view) {
        System.out.println("Log In");
    }

    public static void signUpAdmin(View view){
        System.out.println("Sign up for admins");
    }

    public static void signUpClient(View view) {
        System.out.println("Sign up for clients");
    }
}
