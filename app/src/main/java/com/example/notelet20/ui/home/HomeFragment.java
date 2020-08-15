package com.example.notelet20.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.notelet20.ui.search.ListPlaceHolderFragment;
import com.example.notelet20.R;
import com.example.notelet20.ui.search.SearchFragment;
import static com.example.notelet20.NavBarActivity.toolbar;



public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener, View.OnClickListener {

    public static String newSearch;
    private HomeViewModel homeViewModel;
    View root;
    private Button searchBtn;
    private SearchView search;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
         */

        searchBtn = (Button) root.findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(this);
        search = (SearchView) root.findViewById(R.id.search_bar);
        search.setOnQueryTextListener(this);

        return root;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        newSearch = s;
        FragmentManager fm = this.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, new ListPlaceHolderFragment()).addToBackStack(null);
        transaction.commit();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = this.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, new SearchFragment()).addToBackStack(null);

        transaction.commit();
    }
}