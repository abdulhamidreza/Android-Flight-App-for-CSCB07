package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import cs.b07.cscb07courseproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainClientFragment extends Fragment {

    private static View rootView;

    private static EditText originET, destinationET, dateET, clientPassword, clientFirst, clientLast, clientAddress, clientCreditCard, clientCreditExpiry;

    public MainClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main_client, container, false);

        getActivity().setTitle(R.string.main_title);

        originET = (EditText) rootView.findViewById(R.id.clientOrigin);
        destinationET = (EditText) rootView.findViewById(R.id.clientDestination);
        dateET = (EditText) rootView.findViewById(R.id.clientDate);

        return rootView;
    }

    public static String getClientOrigin(){
        return originET.getText().toString();
    }

    public static String getClientDestination() {
        return  destinationET.getText().toString();
    }

    public static String getClientDate(){
        return dateET.getText().toString();
    }

}
