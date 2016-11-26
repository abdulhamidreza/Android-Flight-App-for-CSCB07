package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cs.b07.cscb07courseproject.LogInActivity;
import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.users.Admin;
import cs.b07.cscb07courseproject.users.Client;
import cs.b07.cscb07courseproject.users.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class Create_User_Fragment extends Fragment {

    private static EditText email,password,firstName,lastName,address,creditCard,creditCardExpiry;
    private static Button createUser;
    private static boolean isClient;
    private RelativeLayout clientInfoContainer;
    private View  rootView;

    private static DateFormat date = new SimpleDateFormat("yyyy-MM-dd");

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
        createUser = (Button) rootView.findViewById(R.id.createUserBtn);
        clientInfoContainer = (RelativeLayout) rootView.findViewById(R.id.clientInfoContainer);

        isClient = getArguments().getBoolean(LogInActivity.isClientKey);

        if(isClient) {
            setClientView();
        }

        return rootView;
    }

    public static boolean getIsClient() {
        return isClient;
    }

    public static User createUser() throws ParseException{
        if (isClient) {
            Date expiryDate = date.parse(creditCardExpiry.getText().toString().replace("/","-"));
            return new Client(email.getText().toString(), password.getText().toString(),
                    firstName.getText().toString(), lastName.getText().toString(),
                    address.getText().toString(), creditCard.getText().toString(),
                    expiryDate);
        } else {
            return new Admin(email.getText().toString(),
                    password.getText().toString());
        }
    }

    private void setClientView(){
        clientInfoContainer.setVisibility(View.VISIBLE);
        createUser.setText(R.string.create_client);
    }


}
