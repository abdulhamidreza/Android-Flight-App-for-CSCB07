package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs.b07.cscb07courseproject.AdminActivity;
import cs.b07.cscb07courseproject.ClientActivity;
import cs.b07.cscb07courseproject.LogInActivity;
import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.flightServices.FlightManager;
import cs.b07.cscb07courseproject.flightServices.FlightService;
import cs.b07.cscb07courseproject.flightServices.InvalidSortException;
import cs.b07.cscb07courseproject.itinerary.Itinerary;

/**
 * A simple List of Itinerary subclass.
 */
public class ItineraryListFragment extends Fragment {

    private static View rootView;
    private static ListView itineraryListView;
    private static ArrayAdapter<String> adapter;
    private static String origin, destination, date;
    private static boolean isDirect, isClient, isIncreasing;
    private static List<Itinerary> itineraries;
    private static List<String> stringItineraries;
    private static FlightService flightService;

    public static final String itineraryKey = "itineraryKey";

    /**
     * The ItineraryListFragment constructor
     */
    public ItineraryListFragment() {
        // Required empty public constructor
    }

    /**
     * Method called when ItineraryListFragment fragment is called to be viewed.
     * @param inflater the inflater of this fragment
     * @param container the container of this fragment
     * @param savedInstanceState savedInstanceState the saved instance of this fragment
     * @return the view set for the user to see
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_itinerary_list, container, false);

        getActivity().setTitle(R.string.itineraries_list_title);

        origin = getArguments().getString(ClientActivity.originKey);
        destination = getArguments().getString(ClientActivity.destinationKey);
        date = getArguments().getString(ClientActivity.dateKey);
        isDirect = getArguments().getBoolean(ClientActivity.isDirectKey);
        isClient = getArguments().getBoolean(LogInActivity.isClientKey);

        if (isClient){
            flightService = new FlightManager(ClientActivity.db.getFlights());
        }else{
            flightService = new FlightManager(AdminActivity.db.getFlights());
        }
        isIncreasing = true;

        try {
            itineraries = flightService.getItinerary(origin, destination, date, isDirect);
        }catch (ParseException ex) {
            ex.printStackTrace();
        }

        stringItineraries = new ArrayList<>();
        for (Itinerary itinerary: itineraries) {
            stringItineraries.add(String.format("%d\n%s --> %s\n$ %f\n%s",
                    itinerary.getItineraryId(), origin, destination,
                    itinerary.getTotalCost(), itinerary.getTotalTimeString()));
        }

        itineraryListView = (ListView) rootView.findViewById(R.id.itineraryListView);
        adapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, stringItineraries);
        itineraryListView.setAdapter(adapter);

        itineraryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(), itineraries.get(position).toString(),
                        Toast.LENGTH_SHORT).show();

                // Switch to detail view of Itinerary
                Bundle bundle = new Bundle();
                bundle.putSerializable(itineraryKey, itineraries.get(position));
                if (isClient) {
                    bundle.putBoolean(LogInActivity.isClientKey, true);
                } else {
                    bundle.putBoolean(LogInActivity.isClientKey, false);
                }
                Fragment frag = new ItineraryBookFragment();
                frag.setArguments(bundle);
                setFragment(frag);
            }
        });

        Button sortTime = (Button) rootView.findViewById(R.id.sortByTimeBtn);
        Button sortCost = (Button) rootView.findViewById(R.id.sortByCostBtn);
        Button sortOrder = (Button) rootView.findViewById(R.id.sortIncreaseBtn);
        sortTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByTime();
            }
        });
        sortCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByCost();
            }
        });
        sortOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSortOrder();
            }
        });

        return rootView;
    }

    /**
     * Changes the fragment to the given fragment.
     * @param fragment the fragment being set to
     */
    private void setFragment (Fragment fragment){
        // changes the fragment
        android.support.v4.app.FragmentTransaction ft =
                getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    /**
     * Sorts Itineraries by time order.
     */
    private void sortByTime() {
        try {
            flightService.sortItineraries(itineraries, "Time", isIncreasing);
            stringItineraries = new ArrayList<>();
            for (Itinerary itinerary: itineraries) {
                stringItineraries.add(String.format("%d\n%s --> %s\n$ %f\n%s",
                        itinerary.getItineraryId(), origin, destination,
                        itinerary.getTotalCost(), itinerary.getTotalTimeString()));
            }
            adapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, stringItineraries);
            itineraryListView.setAdapter(adapter);
        }catch(InvalidSortException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sorts Itineraries by cost order.
     */
    private void sortByCost() {
        try {
            flightService.sortItineraries(itineraries, "Cost", isIncreasing);
            stringItineraries = new ArrayList<>();
            for (Itinerary itinerary: itineraries) {
                stringItineraries.add(String.format("%d\n%s --> %s\n$ %f\n%s",
                        itinerary.getItineraryId(), origin, destination,
                        itinerary.getTotalCost(), itinerary.getTotalTimeString()));
            }
            adapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, stringItineraries);
            itineraryListView.setAdapter(adapter);
        }catch(InvalidSortException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Reverses the sorting order (descending -> ascending, ascending -> descending).
     */
    private void changeSortOrder(){
        if (isIncreasing){
            isIncreasing = false;
        }else{
            isIncreasing = true;
        }
        Collections.reverse(itineraries);
        Collections.reverse(stringItineraries);
        adapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, stringItineraries);
        itineraryListView.setAdapter(adapter);
    }

}
