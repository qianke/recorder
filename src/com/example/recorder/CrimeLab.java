package com.example.recorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;

public class CrimeLab {
	private static final String TAG = "CrimeLab";
	private static final String FILENAME = "crimes.json";
	
	
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	
	private ArrayList<Crime> mCrimes;
	private CrimeJSONSerializer mCrimeJSON;
	
	private CrimeLab(Context context) {
		mAppContext = context;
		mCrimeJSON = new CrimeJSONSerializer(mAppContext, FILENAME);
		//mCrimes = new ArrayList<Crime>();
		try {
			mCrimes = mCrimeJSON.loadCrimes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mCrimes = new ArrayList<Crime>();
			
			Log.d(TAG, "Error loading crimes: " + e);
		}
	}

	public static CrimeLab get(Context c) {
		if (sCrimeLab == null) {
			sCrimeLab = new CrimeLab(c.getApplicationContext());
		}
		return sCrimeLab;
	}

	public void addCrime(Crime c) {
		mCrimes.add(c);
	}

	public ArrayList<Crime> getCrimes() {
		return mCrimes;
	}

	public Crime getCrime(UUID id) {
		for (Crime c : mCrimes) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}
	
	public boolean saveCrimes() {
		try {
			mCrimeJSON.saveCrimes(mCrimes);
			Log.d(TAG, "cirmes saves to file: " + FILENAME);
			return true;
		} catch (Exception e){
			Log.d(TAG, "Error saving crimes: ", e);
			return false;
		}
	}
}
