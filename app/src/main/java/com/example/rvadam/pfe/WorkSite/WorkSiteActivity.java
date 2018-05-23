package com.example.rvadam.pfe.WorkSite;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;

import com.example.rvadam.pfe.DatePicker.DatePickerFragment;
import com.example.rvadam.pfe.FirebaseDBHelpers.PeopleDBHelper;
import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

//import static com.google.android.gms.common.GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
//import static com.google.android.gms.common.GooglePlayServicesUtilLight.isGooglePlayServicesAvailable;

public class WorkSiteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "WorkSiteActivity";

    private static WorkSite innerWorksite;
    private WorksiteFragment workSiteFragment;
    PeopleDBHelper peopleDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksite);

        peopleDBHelper = new PeopleDBHelper("people", this);
        peopleDBHelper.retrievePeopleBis();

        if (findViewById(R.id.worksiteFragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            workSiteFragment = WorksiteFragment.newInstance(innerWorksite);
            ft.replace(R.id.worksiteFragment_container, workSiteFragment);
            ft.commit();
        }
    }

    public static void setInnerWorksite(com.example.rvadam.pfe.Model.WorkSite workSite) {
        innerWorksite = workSite;
    }

    public void refreshListOfPeopleForWorksite() {
        workSiteFragment.setPeopleForWorksite();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        workSiteFragment.setDate(cal);
    }


    /**
     * This callback method, call DatePickerFragment class,
     * DatePickerFragment class returns calendar view.
     *
     * @param view
     */
    public void datePicker(View view) {

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "date");
    }


}