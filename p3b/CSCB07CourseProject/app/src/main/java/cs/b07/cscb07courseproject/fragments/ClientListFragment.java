package cs.b07.cscb07courseproject.fragments;


import android.content.Intent;
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

import cs.b07.cscb07courseproject.AdminActivity;
import cs.b07.cscb07courseproject.ClientActivity;
import cs.b07.cscb07courseproject.LogInActivity;
import cs.b07.cscb07courseproject.R;
import cs.b07.cscb07courseproject.flightServices.FlightManager;
import cs.b07.cscb07courseproject.itinerary.Itinerary;
import cs.b07.cscb07courseproject.users.Client;

import static cs.b07.cscb07courseproject.AdminActivity.db;
import static cs.b07.cscb07courseproject.LogInActivity.dataKey;
import static cs.b07.cscb07courseproject.LogInActivity.userKey;

/**
 * A simple List of Clients subclass.
 */
public class ClientListFragment extends Fragment {

    private static View rootView;

    public ClientListFragment() {
        // Required empty public constructor
    }

    /**
     * Method called when ClientListFragment fragment is called to be viewed.
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

        final List<Client> clients = db.getClients();

        List<String> stringClients = new ArrayList<>();
        for (Client client: clients) {
            stringClients.add(String.format("%s\n%s %s",
                    client.getEmail(),client.getFirstName(), client.getLastName()));
        }
        ListView clientListView = (ListView) rootView.findViewById(R.id.clientListView);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, stringClients);
        clientListView.setAdapter(adapter);

        clientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(), clients.get(position).toString(),
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), ClientActivity.class);
                intent.putExtra(userKey, clients.get(position).getEmail());
                intent.putExtra(dataKey, db);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
