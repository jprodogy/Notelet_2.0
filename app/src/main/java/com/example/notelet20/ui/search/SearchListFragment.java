package com.example.notelet20.ui.search;



import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;


import com.example.notelet20.R;
import com.example.notelet20.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SearchListFragment extends ListFragment {
    public static Note b;
    OnBodySelectedListener mListener;
    private ArrayList<Note> elements;
    private DatabaseReference dataRef;
    private FirebaseAuth authRef;
    private String username;


    public SearchListFragment() {
    } // empty constructor req'd

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        elements = new ArrayList<>();
        authRef = FirebaseAuth.getInstance();
        dataRef = FirebaseDatabase.getInstance().getReference("Users").child(authRef.getCurrentUser().getUid()).child("Documents");

        ReadInDatabase();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        b = elements.get(position);
        Log.wtf("MainActivity", elements.get(position).toString());
        mListener.onBodySelected(b);

        FragmentManager fm = this.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, new NoteViewerFragment()).addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnBodySelectedListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(
                    context.toString() + " must implement OnBodySelectedListener");
        }
    }

    public interface OnBodySelectedListener {
        void onBodySelected(Note b);
    }

    public void ReadInDatabase(){
        // Read from the database
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Log.d("bruh", postSnapshot.getValue().toString());
                    Note note = postSnapshot.getValue(Note.class);
                    Log.d("bruh2", note.toString());
                    elements.add(note);

                }

                SearchNotes();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void SearchNotes(){
        ArrayList<Note> searchSubjects = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            if (HomeFragment.newSearch.equalsIgnoreCase(elements.get(i).getSubject())){
                searchSubjects.add(elements.get(i));
            }
        }

        ArrayAdapter<Note> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, searchSubjects);
        Log.wtf("MainActivity", elements.toString());
        setListAdapter(adapter);
    }
}
