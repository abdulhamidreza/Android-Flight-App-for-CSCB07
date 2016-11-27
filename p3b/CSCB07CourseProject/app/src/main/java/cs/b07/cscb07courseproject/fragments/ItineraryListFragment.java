package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cs.b07.cscb07courseproject.ClientActivity;
import cs.b07.cscb07courseproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItineraryListFragment extends Fragment {

    private static View rootView;
    public ItineraryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_itinerary_list, container, false);
        return rootView;
    }

}
