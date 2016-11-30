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
import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.flightServices.FlightManager;
import cs.b07.cscb07courseproject.flightServices.FlightService;
import cs.b07.cscb07courseproject.itinerary.Itinerary;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItineraryListFragment extends Fragment {

    private static View rootView;
    private static ListView itineraryListView;
    private static ArrayAdapter<String> adapter;
    private static String origin, destination, date;
    private static boolean isDirect;
    private static List<Itinerary> itineraries;
    private static List<String> stringItineraries;

    public ItineraryListFragment() {
        // Required empty public constructor
    }


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

        FlightService flightService
                = new FlightManager(null);
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
            }
        });

        return rootView;
    }

}
