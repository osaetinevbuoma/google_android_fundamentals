package com.modnsolutions.androidfragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    OnButtonClickedListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Get button from MainFragment and add an OnClickListener to handle the click event
        // to send the message to SecondFragment
        Button button = rootView.findViewById(R.id.button_fragment_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("Clicking button sent this message to Second Fragment");
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnButtonClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement OnButtonClickedListener"); // Ensure hosting activity implements
        }
    }

    /**
     * Listener to be implemented by hosting activity
     */
    public interface OnButtonClickedListener {
        void onButtonClicked(String message);
    }

}
