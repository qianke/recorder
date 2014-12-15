package com.example.recorder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickerFragment extends DialogFragment {
	public static final String EXTRA_DATE = "crime_date";

	private Date mDate;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mDate = (Date) getArguments().getSerializable(EXTRA_DATE);
		
		Log.d("xiong","onCreateDialog_"+mDate);

		// Create c Calendar to get the year, month, and day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		View v = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_date, null);

		DatePicker datePicker = (DatePicker) v
				.findViewById(R.id.dialog_date_datePicker);
		datePicker.init(year, month, day, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year, int month,
					int day) {
				// TODO Auto-generated method stub
				mDate = new GregorianCalendar(year, month, day).getTime();

				// update arguments to preserve selected value on rotation
				// wait add
				// ...

			}
		});

		return new AlertDialog.Builder(getActivity())
				.setView(v)
				.setTitle(R.string.date_picker_title)
				.setPositiveButton(android.R.string.ok, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						sendResult(Activity.RESULT_OK);
					}
				}).create();
	}
	
	private void sendResult(int resultCode){
		if(getTargetFragment() == null){
			return;
		}
		Intent i = new Intent();
		i.putExtra(EXTRA_DATE, mDate);
		
		Log.d("xiong","sendResult");
		
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}

	public static DatePickerFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);

		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setArguments(args);
		return fragment;
	}
}
