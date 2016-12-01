package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cs.b07.cscb07courseproject.LogInActivity;
import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.itinerary.Itinerary;
import static cs.b07.cscb07courseproject.ClientActivity.db;
import static cs.b07.cscb07courseproject.ClientActivity.client;

/**
 * A simple Itinerary Book subclass.
 */
public class ItineraryBookFragment extends Fragment {

    private static View rootView;
    private static boolean isClient;
    private static Itinerary itinerary;

    /**
     * The ItineraryBookFragment constructor.
     */
    public ItineraryBookFragment() {
        // Required empty public constructor
    }

    /**
     * Method called when ItineraryBookFragment fragment is called to be viewed.
     * @param inflater the inflater of this fragment
     * @param container the container of this fragment
     * @param savedInstanceState savedInstanceState the saved instance of this fragment
     * @return the view set for the user to see
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_itinerary_book, container, false);

        getActivity().setTitle(R.string.book_itinerary_title);

        itinerary = (Itinerary) getArguments().getSerializable(ItineraryListFragment.itineraryKey);
        isClient = getArguments().getBoolean(LogInActivity.isClientKey);

        Button btn = (Button) rootView.findViewById(R.id.itineraryBookBtn);
        TextView tv = (TextView) rootView.findViewById(R.id.itineraryDetail);

        if (!isClient) {
            btn.setVisibility(View.GONE);
        }

        tv.setText(itinerary.toString());

        return rootView;
    }

    /**
     * Books an itinerary.
     * @return Returns true if itinerary is booked successfully
     */
    public static boolean bookItinerary() {
        if (db.getClient(client).book(itinerary)) {
            return true;
        }else{
            return false;
        }
    }

}
