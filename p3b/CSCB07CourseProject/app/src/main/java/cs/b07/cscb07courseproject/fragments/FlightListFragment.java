package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cs.b07.cscb07courseproject.ClientActivity;
import cs.b07.cscb07courseproject.LogInActivity;
import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.flightServices.FlightManager;
import cs.b07.cscb07courseproject.flightServices.FlightService;
import cs.b07.cscb07courseproject.itinerary.Itinerary;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlightListFragment extends Fragment {

    private static View rootView;
    private static ListView flightListView;
    private static ArrayAdapter<String> adapter;
    private static String origin, destination, date;
    private static boolean isDirect, isClient;
    private static List<Itinerary> itineraries;
    private static List<String> stringItineraries;

    public static final String flightKey = "flightKey";

    public FlightListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_flight_list, container, false);

        getActivity().setTitle(R.string.flight_list_title);

        origin = getArguments().getString(ClientActivity.originKey);
        destination = getArguments().getString(ClientActivity.destinationKey);
        date = getArguments().getString(ClientActivity.dateKey);
        isDirect = getArguments().getBoolean(ClientActivity.isDirectKey);
        isClient = getArguments().getBoolean(LogInActivity.isClientKey);

        FlightService flightService
                = new FlightManager(ClientActivity.db.getFlights());
        try {
            itineraries = flightService.getItinerary(origin, destination, date, isDirect);
        }catch (ParseException ex) {
            ex.printStackTrace();
        }

        stringItineraries = new ArrayList<>();
        for (Itinerary itinerary: itineraries) {
            stringItineraries.add(String.format("%d\n%s --> %s\n$ %f\n%s",
                    itinerary.getFlights().get(0).getFlightNum(), origin, destination,
                    itinerary.getTotalCost(), itinerary.getTotalTimeString()));
        }
        flightListView = (ListView) rootView.findViewById(R.id.flightList);

        adapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, stringItineraries);
        flightListView.setAdapter(adapter);

        flightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(), itineraries.get(position).toString(),
                        Toast.LENGTH_SHORT).show();

                // Switch to detail view of Itinerary
                Bundle bundle = new Bundle();
                bundle.putSerializable(flightKey, itineraries.get(position).getFlights().get(0));
                if (isClient) {
                    bundle.putBoolean(LogInActivity.isClientKey, true);
                } else {
                    bundle.putBoolean(LogInActivity.isClientKey, false);
                }
                Fragment frag = new ViewFlightFragment();
                frag.setArguments(bundle);
                setFragment(frag);
            }
        });



        return rootView;
    }

    private void setFragment (Fragment fragment){
        // changes the fragment
        android.support.v4.app.FragmentTransaction ft =
                getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

}
