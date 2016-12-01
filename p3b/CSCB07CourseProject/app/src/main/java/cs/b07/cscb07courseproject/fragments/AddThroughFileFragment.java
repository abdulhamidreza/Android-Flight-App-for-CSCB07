package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cs.b07.cscb07courseproject.R;

/**
 * A simple AddThroughFile subclass.
 */
public class AddThroughFileFragment extends Fragment {

    private static View rootView;


    public AddThroughFileFragment() {
        // Required empty public constructor
    }

    /**
     * Method called when AddThroughFile fragment is called.
     * @param inflater the inflater of this fragment
     * @param container the container of this fragment
     * @param savedInstanceState savedInstanceState the saved instance of this fragment
     * @return the view set for the user to see
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_through_file, container, false);

        getActivity().setTitle(R.string.add_file_title);

        return rootView;
    }

}
