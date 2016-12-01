package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cs.b07.cscb07courseproject.ClientActivity;
import cs.b07.cscb07courseproject.R;

import static cs.b07.cscb07courseproject.ClientActivity.db;
import static cs.b07.cscb07courseproject.ClientActivity.client;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientProfileFragment extends Fragment {

    private static View rootView;

    public ClientProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_client_profile, container, false);

        getActivity().setTitle(R.string.view_profile);

        TextView clientProfile = (TextView) rootView.findViewById(R.id.clientProfile);
        clientProfile.setText(db.getClient(client).toString());

        return rootView;
    }

}
