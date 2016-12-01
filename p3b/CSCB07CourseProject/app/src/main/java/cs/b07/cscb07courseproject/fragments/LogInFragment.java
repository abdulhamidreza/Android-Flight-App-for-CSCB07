package cs.b07.cscb07courseproject.fragments;

import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cs.b07.cscb07courseproject.LogInActivity;
import cs.b07.cscb07courseproject.R;

/**
 * A simple Login subclass.
 */
public class LogInFragment extends Fragment {

    private View rootView;

    /**
     * The LoginFragment constructor.
     */
    public LogInFragment() {
        // Required empty public constructor
    }

    /**
     * Method called when LoginFragment fragment is called to be viewed.
     * @param inflater the inflater of this fragment
     * @param container the container of this fragment
     * @param savedInstanceState savedInstanceState the saved instance of this fragment
     * @return the view set for the user to see
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_log_in, container, false);

        getActivity().setTitle(R.string.log_in_title);

        return rootView;
    }


}
