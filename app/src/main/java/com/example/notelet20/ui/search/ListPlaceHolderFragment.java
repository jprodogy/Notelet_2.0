package com.example.notelet20.ui.search;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.notelet20.R;

import static com.example.notelet20.NavBarActivity.toolbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListPlaceHolderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPlaceHolderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListPlaceHolderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListPlaceHolderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListPlaceHolderFragment newInstance(String param1, String param2) {
        ListPlaceHolderFragment fragment = new ListPlaceHolderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        showActionBar(); // the method to change ActionBar
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_place_holder, container, false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // add implementation when user select an item from the menu
        switch (item.getItemId()) {
            case R.id.action_settings:
                // do something
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showActionBar() {

        // get the ActionBar from Main Activity
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        // inflate the customized Action Bar View
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_actionbar, null);

        if (actionBar != null) {
            // enable the customized view and disable title
            actionBar.setDisplayShowCustomEnabled(true);


            actionBar.setCustomView(v);
            // remove Burger Icon
            toolbar.setNavigationIcon(null);

            // add click listener to the back arrow icon
            v.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // reverse back the show
                    actionBar.setDisplayShowCustomEnabled(false);
                    actionBar.setDisplayShowTitleEnabled(true);
                    //get the Drawer and DrawerToggle from Main Activity
                    // set them back as normal
                    DrawerLayout drawer = getActivity().findViewById(R.id.drawer_layout);
                    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                            getActivity(), drawer, toolbar, R.string.navigation_drawer_open,
                            R.string.navigation_drawer_close);
                    // All that to re-synchronize the Drawer State
                    toggle.syncState();
                    // Implement Back Arrow Icon
                    // so it goes back to previous Fragment
                    getActivity().onBackPressed();
                }
            });
        }
    }
}