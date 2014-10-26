package com.example.recorder;

import android.app.ListFragment;
import android.os.Bundle;

public class CrimeListFragment extends ListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		CrimeLab.get(getActivity()).getCrimes();
	}
}
