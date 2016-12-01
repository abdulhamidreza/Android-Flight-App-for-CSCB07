package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs.b07.cscb07courseproject.R;

/**
 * A simple List of Clients subclass.
 */
public class ClientListFragment extends Fragment {

    private static View rootView;

    public ClientListFragment() {
        // Required empty public constructor
    }

    /**
     * Method called when onCreateView fragment is called.
     * @param inflater the inflater of this fragment
     * @param container the container of this fragment
     * @param savedInstanceState savedInstanceState the saved instance of this fragment
     * @return the view set for the user to see
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_client_list, container, false);

        getActivity().setTitle(R.string.client_list_title);

        return rootView;
    }

}
