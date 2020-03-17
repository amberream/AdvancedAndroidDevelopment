package com.example.android.fragmentexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    public static final int YES = 0;
    public static final int NO = 1;

    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment newInstance()
    {
        return new SimpleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        final RatingBar ratingBar = rootView.findViewById(R.id.song_rating);

        // Set the radioGroup onCheckedChanged listener.
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                TextView textView = rootView.findViewById(R.id.fragment_header);
                if (checkedId == R.id.radioButton_yes)
                {
                    textView.setText(R.string.yes_message);
                }
                else if (checkedId == R.id.radioButton_no)
                {
                    textView.setText(R.string.no_message);
                }
            }
        });

        // Set the rating bar onRatingBarChanged listenerr
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(getActivity(), "My Rating: " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            }
        });

        // Return the View for the fragment's UI.
        return rootView;
    }

}
