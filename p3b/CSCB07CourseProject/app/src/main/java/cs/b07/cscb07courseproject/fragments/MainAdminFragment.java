package cs.b07.cscb07courseproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import cs.b07.cscb07courseproject.AdminActivity;
import cs.b07.cscb07courseproject.ClientActivity;
import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.util.ValidDate;

/**
 * A simple Main Admin subclass.
 */
public class MainAdminFragment extends Fragment {

    private static View rootView;

    private static EditText originET, destinationET, dateET;

    /**
     * The MainAdminFragment constructor.
     */
    public MainAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Method called when MainAdminFragment fragment is called to be viewed.
     * @param inflater the inflater of this fragment
     * @param container the container of this fragment
     * @param savedInstanceState savedInstanceState the saved instance of this fragment
     * @return the view set for the user to see
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main_admin, container, false);

        getActivity().setTitle(R.string.main_title);


        originET = (EditText) rootView.findViewById(R.id.adminOrigin);
        destinationET = (EditText) rootView.findViewById(R.id.adminDestination);
        dateET = (EditText) rootView.findViewById(R.id.adminDate);

        return rootView;
    }


    /**
     * Returns the flight detail in a bundle.
     * @return the bundle with flight details
     */
    public static Bundle bundleFlightDetail(){
        Bundle bundle = new Bundle();
        String origin = originET.getText().toString();
        String destination = destinationET.getText().toString();
        String date = dateET.getText().toString();
        if (!origin.equals("") && !destination.equals("") && !date.equals("") && ValidDate.validDate(date)){
            bundle.putString(AdminActivity.originKey, origin);
            bundle.putString(AdminActivity.destinationKey, destination);
            bundle.putString(AdminActivity.dateKey, date);
        }
        return bundle;
    }

}
