/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentexample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener{

    private static final String STATE_FRAGMENT = "state_of_fragment";

    private Button button;
    private boolean fragmentDisplayed;

    private int radioButtonChoice = SimpleFragment.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button_open);

        if (savedInstanceState != null)
        {
            fragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (fragmentDisplayed)
            {
                button.setText(R.string.close);
            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // sample calls super last - is that necessary?
        super.onSaveInstanceState(outState);
        // fragments are not destroyed on config changes so no need to save any of that
        // but we need to save the state of the button to reflect if the fragment is open
        outState.putBoolean(STATE_FRAGMENT, fragmentDisplayed);
    }

    public void openFragment(View view) {
        if (fragmentDisplayed)
        {
            closeFragment();
        }
        else
        {
            displayFragment();
        }
    }

    public void displayFragment()
    {
        SimpleFragment simpleFragment = SimpleFragment.newInstance(radioButtonChoice);
        // get the fragment manager and start a new transaction
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // add the fragment to the container (add to backstack allows the user to
        // return to the previous fragment state with the back button)
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit();

        // update the ui
        button.setText(R.string.close);
        fragmentDisplayed = true;
    }

    public void closeFragment()
    {
        FragmentManager fragmentManager = getFragmentManager();
        // find the fragment by the container id
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragment != null)
        {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }

        // update the ui
        button.setText(R.string.open);
        fragmentDisplayed = false;
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        radioButtonChoice = choice;
        Toast.makeText(this, "Radio button choice is " + radioButtonChoice, Toast.LENGTH_SHORT).show();
    }
}
