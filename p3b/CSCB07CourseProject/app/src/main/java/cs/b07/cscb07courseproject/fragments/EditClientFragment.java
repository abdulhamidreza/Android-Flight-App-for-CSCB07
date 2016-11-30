package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
        creditCardExpiry.setText(client.getCreditExpiry().toString());
        return rootView;
    }

    public static boolean updateProfile(){
        password.getText().toString();
        firstName.getText().toString();
        lastName.getText().toString();
        address.getText().toString();
        creditCard.getText().toString();
        creditCardExpiry.getText().toString();
        return true;
    }
}
