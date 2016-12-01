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
import cs.b07.cscb07courseproject.util.ValidDate;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateUserFragment extends Fragment {

    private static EditText email,password,firstName,lastName,address,creditCard,creditCardExpiry;
    private static Button createUser;
    private static boolean isClient;
    private RelativeLayout clientInfoContainer;
    private View  rootView;

    public CreateUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_user, container, false);

        getActivity().setTitle(R.string.create_admin_title);

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
            getActivity().setTitle(R.string.create_client_title);
            setClientView();
        }

        return rootView;
    }

    public static boolean getIsClient() {
        return isClient;
    }

    public static User createUser() throws ParseException, NullPointerException{
        if (isClient) {
            String ccExpiryDate = creditCardExpiry.getText().toString();
            if (ValidDate.validDate(ccExpiryDate)){
                if (!email.getText().toString().equals("")
                    && !password.getText().toString().equals("") && !firstName.getText().toString().equals("")
                    && !lastName.getText().toString().equals("") && !address.getText().toString().equals("")
                    && !creditCard.getText().toString().equals("")) {
                    return new Client(email.getText().toString(), password.getText().toString(),
                            firstName.getText().toString(), lastName.getText().toString(),
                            address.getText().toString(), creditCard.getText().toString(),
                            ccExpiryDate);
                }else{
                    throw new NullPointerException();
                }
            }else{
                throw new ParseException("Unparseable date", 10);
            }
        } else {
            if (!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
                return new Admin(email.getText().toString(),
                        password.getText().toString());
            }else {
                throw new NullPointerException();
            }
        }
    }

    private void setClientView(){
        clientInfoContainer.setVisibility(View.VISIBLE);
        createUser.setText(R.string.create_client);
    }


}
