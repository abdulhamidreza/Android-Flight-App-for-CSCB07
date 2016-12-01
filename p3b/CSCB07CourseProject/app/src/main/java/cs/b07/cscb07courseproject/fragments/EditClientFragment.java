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

import cs.b07.cscb07courseproject.R;
import static cs.b07.cscb07courseproject.ClientActivity.db;
import static cs.b07.cscb07courseproject.ClientActivity.client;

/**
 * A simple Edit Client subclass.
 */
public class EditClientFragment extends Fragment {

    private static EditText password,firstName,lastName,address,creditCard,creditCardExpiry;
    private static View rootView;

    private static DateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * The EditClientFragment constructor
     */
    public EditClientFragment() {
        // Required empty public constructor
    }

    /**
     * Method called when EditClientFragment fragment is called to be viewed.
     * @param inflater the inflater of this fragment
     * @param container the container of this fragment
     * @param savedInstanceState savedInstanceState the saved instance of this fragment
     * @return the view set for the user to see
     */
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
        password.setText(db.getClient(client).getPassword());
        firstName.setText(db.getClient(client).getFirstName());
        lastName.setText(db.getClient(client).getLastName());
        address.setText(db.getClient(client).getAddress());
        creditCard.setText(db.getClient(client).getCreditCard());
        creditCardExpiry.setText(date.format(db.getClient(client).getCreditExpiry()));
        return rootView;
    }

    /**
     * Updates the client's profile
     * @return returns true if profile is successfully updated.
     */
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
                db.getClient(client).setPassword(pass);
                db.getClient(client).setFirstName(fName);
                db.getClient(client).setLastName(lName);
                db.getClient(client).setAddress(addrs);
                db.getClient(client).setCreditCard(cCard);
                db.getClient(client).setCreditExpiry(cCardExpiry);
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
