package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import cs.b07.cscb07courseproject.LogInActivity;
import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.users.Admin;
import cs.b07.cscb07courseproject.users.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class Create_User_Fragment extends Fragment {

    private static EditText email,password,firstName,lastName,address,creditCard,creditCardExpiry;
    private static Button createUser;
    private static RelativeLayout clientInfoContainer;
    private static View  rootView;
    public Create_User_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_user, container, false);

        email = (EditText) rootView.findViewById(R.id.emailET);
        password = (EditText) rootView.findViewById(R.id.passwordET);
        firstName = (EditText) rootView.findViewById(R.id.firstName);
        lastName = (EditText) rootView.findViewById(R.id.lastName);
        address = (EditText) rootView.findViewById(R.id.address);
        creditCard = (EditText) rootView.findViewById(R.id.creditCard);
        creditCardExpiry = (EditText) rootView.findViewById(R.id.creditCardExpiry);
        createUser = (Button) rootView.findViewById(R.id.createAdminBtn);
        clientInfoContainer = (RelativeLayout) rootView.findViewById(R.id.clientInfoContainer);

        if(getArguments().getBoolean(LogInActivity.isClientKey)) {
            setClientView();
        }

        return rootView;
    }

    public static User createAdmin(){
        return new Admin(email.getText().toString(),
                password.getText().toString());
    }

    private void setClientView(){
        clientInfoContainer.setVisibility(View.VISIBLE);
        createUser.setText(R.string.create_client);
    }


}
