package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import cs.b07.cscb07courseproject.LogInActivity;
import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.flight.Flight;

/**
 * A simple View Flight subclass.
 */
public class ViewFlightFragment extends Fragment {

    private static View rootView;
    private static EditText viewFlightNum,viewOrigin, viewDestination, viewAirline,
    viewDepDate, viewArrivalDate, viewSeats, viewCost;
    private static Button updateFlightBtn;
    private static Flight flight;
    private static boolean isClient;

    private static DateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    /**
     * The ViewFlightFragment constructor
     */
    public ViewFlightFragment() {
        // Required empty public constructor
    }

    /**
     * Method called when ViewFlightFragment fragment is called to be viewed.
     * @param inflater the inflater of this fragment
     * @param container the container of this fragment
     * @param savedInstanceState savedInstanceState the saved instance of this fragment
     * @return the view set for the user to see
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_view_flight, container, false);

        getActivity().setTitle(R.string.flight_detail_title);

        viewFlightNum = (EditText) rootView.findViewById(R.id.viewFlightNumber);
        viewOrigin = (EditText) rootView.findViewById(R.id.viewOrigin);
        viewDestination = (EditText) rootView.findViewById(R.id.viewDestination);
        viewAirline = (EditText) rootView.findViewById(R.id.viewAirline);
        viewDepDate = (EditText) rootView.findViewById(R.id.viewDepartureDate);
        viewArrivalDate = (EditText) rootView.findViewById(R.id.viewArrivalDate);
        viewSeats = (EditText) rootView.findViewById(R.id.viewAvailableSeats);
        viewCost = (EditText) rootView.findViewById(R.id.viewCost);

        updateFlightBtn = (Button) rootView.findViewById(R.id.updateFlightBtn);

        flight = (Flight) getArguments().getSerializable(FlightListFragment.flightKey);
        isClient = getArguments().getBoolean(LogInActivity.isClientKey);

        setFlightInfo();

        if (!isClient){
            setAdminView();
        }

        return rootView;
    }

    /**
     * Sets the view of the user to the admin's and allows clickable permissions
     */
    private void setAdminView (){
        viewFlightNum.setClickable(true);
        viewOrigin.setClickable(true);
        viewDestination.setClickable(true);
        viewAirline.setClickable(true);
        viewDepDate.setClickable(true);
        viewArrivalDate.setClickable(true);
        viewSeats.setClickable(true);
        viewCost.setClickable(true);

        updateFlightBtn.setVisibility(View.VISIBLE);
    }

    /**
     * Sets flight's information with the given information
     */
    private void setFlightInfo() {
        viewFlightNum.setText(flight.getFlightNum());
        viewOrigin.setText(flight.getOrigin());
        viewDestination.setText(flight.getDestination());
        viewAirline.setText(flight.getAirline());
        viewDepDate.setText(dateTime.format(flight.getDepartureDate().getTime()));
        viewAirline.setText(dateTime.format(flight.getArrivalDate().getTime()));
        viewSeats.setText(flight.getAvailableSeats());
        viewCost.setText(String.format("$%f",flight.getCost()));
    }

    /**
     * Updates the flight's information with the given information
     * @return returns true if flight was updated successfully
     */
    public static boolean updateFlightInfo(){
        try {
            flight.setFlightNum(viewFlightNum.getText().toString());
            flight.setOrigin(viewOrigin.getText().toString());
            flight.setDestination(viewDestination.getText().toString());
            flight.setAirline(viewAirline.getText().toString());
            flight.setDepartureDate(viewDepDate.getText().toString());
            flight.setArrivalDate(viewArrivalDate.getText().toString());
            flight.setAvailableSeats(Integer.parseInt(viewSeats.getText().toString()));
            flight.setCost(Double.parseDouble(viewCost.getText().toString().substring(1)));
            // update flight info with database
            return true;
        }catch(ParseException ex){
            return false;
        }
    }

}
