package com.modnsolutions.androidfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    public static final String MESSAGE = "SECOND_FRAG";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        // Get bundle send from MainFragment through MainActivity (tablets) or
        // SecondActivity (phones) and put into TextView
        Bundle bundle = getArguments();
        if (bundle != null) {
            String message = bundle.getString(MESSAGE);
            TextView textView = rootView.findViewById(R.id.textview_fragment_second);
            textView.setText(message);
        }

        return rootView;
    }

}
