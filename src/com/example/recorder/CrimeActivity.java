package com.example.recorder;

import android.app.Fragment;

public class CrimeActivity extends SingleFragmentActivity {
	
	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new CrimeFragment();
	}
}
