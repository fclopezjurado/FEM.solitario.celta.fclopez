package es.upm.miw.SolitarioCelta.fragments;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import es.upm.miw.SolitarioCelta.R;

public class SCeltaFragmentoPrefs extends PreferenceFragment{

    public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
    }
}
