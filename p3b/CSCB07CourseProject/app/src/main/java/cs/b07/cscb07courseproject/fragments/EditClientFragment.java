package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import cs.b07.cscb07courseproject.ClientActivity;
import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.users.Client;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditClientFragment extends Fragment {

    private static EditText password,firstName,lastName,address,creditCard,creditCardExpiry;
    private static View rootView;
    private static Client client;

    private static DateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    public EditClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_edit_client, container, false);

        getActivity().setTitle(R.string.edit_profile);
        password = (EditText) rootView.findViewById(R.id.clientPassword);
        firstName = (EditText) rootView.findViewById(R.id.clientFirstName);
        lastName = (EditText) rootView.findViewById(R.id.clientLastName);
        address = (EditText) rootView.findViewById(R.id.clientAddress);
        creditCard = (EditText) rootView.findViewById(R.id.clientCreditCard);
        creditCardExpiry = (EditText) rootView.findViewById(R.id.clientCreditExpiry);
        client = (Client)getArguments().getSerializable(ClientActivity.clientKey);
        password.setText(client.getPassword());
        firstName.setText(client.getFirstName());
        lastName.setText(client.getLastName());
        address.setText(client.getAddress());
        creditCard.setText(client.getCreditCard());
        creditCardExpiry.setText(date.format(client.getCreditExpiry()));
        return rootView;
    }

    public static boolean updateProfile(){
        try {
            String pass = password.getText().toString();
            String fName = firstName.getText().toString();
            String lName = lastName.getText().toString();
            String addrs = address.getText().toString();
            String cCard = creditCard.getText().toString();
            String cCardExpiry = creditCardExpiry.getText().toString();
            if (!(pass.equals("") || fName.equals("") || lName.equals("") || addrs.equals("")
                    || cCard.equals("") || cCardExpiry.equals(""))) {
                client.setPassword(pass);
                client.setFirstName(fName);
                client.setLastName(lName);
                client.setAddress(addrs);
                client.setCreditCard(cCard);
                client.setCreditExpiry(cCardExpiry);
            } else {
                return false;
            }
            // add update client profile to database
            return true;
        }catch(ParseException ex){
            return false;
        }
    }
}
