package com.example.rvadam.pfe.WorkSite;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rvadam.pfe.LocationHelper.LocationHelper;
import com.example.rvadam.pfe.Model.WorkSite;
import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.Utils.PeopleManager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by rdelfoss on 22/05/2018.
 */

public class WorksiteFragment extends Fragment {
    private static final String TAG = "WorksiteFragment";

    private ArrayAdapter<String> adapter;
    private static final String DESCRIBABLE_KEY = "describable_key";
    private WorkSite innerWorksite;
    String address = "";

    TextView worksite_name;
    TextView worksite_vic;
    ImageButton image_date_picker;
    TextView worksite_type;
    TextView worksite_address;
    ListView woksite_people;
    Button button_photos;
    Button button_docs;
    Button button_generate_pdf;
    Button save_worksite;

    public static WorksiteFragment newInstance(WorkSite innerWorksite) {
        WorksiteFragment fragment = new WorksiteFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, innerWorksite);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        innerWorksite = (WorkSite) getArguments().getSerializable(DESCRIBABLE_KEY);

        return inflater.inflate(R.layout.fragment_worksite, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupUI(getActivity().findViewById(R.id.worksite_fragment));

        worksite_name = getActivity().findViewById(R.id.worksite_name);
        worksite_vic = getActivity().findViewById(R.id.worksite_vic);
        image_date_picker = getActivity().findViewById(R.id.image_date_picker);
        worksite_type = getActivity().findViewById(R.id.worksite_type);
        worksite_address = getActivity().findViewById(R.id.worksite_address);
        woksite_people = getActivity().findViewById(R.id.fourth_row);
        button_photos = getActivity().findViewById(R.id.button_photos);
        button_docs = getActivity().findViewById(R.id.button_docs);
        button_generate_pdf = getActivity().findViewById(R.id.button_generate_pdf);
        save_worksite = getActivity().findViewById(R.id.save_worksite);

        long dVIC = innerWorksite.getDateVIC();
        Date dateVic = getDateFromTimestamp(dVIC);
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);


        worksite_name.setText(innerWorksite.getName());
        worksite_type.setText(innerWorksite.getType());
        worksite_vic.setText(dateFormat.format(dateVic));

        getAddress(innerWorksite.getLattitude(), innerWorksite.getLongitude());
        worksite_address.setText(address);

    }

    public Date getDateFromTimestamp(long t) {
        return new Date(t);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public void setPeopleForWorksite() {
        List<String> personsById = innerWorksite.getEmployees();
        List<String> personsByLastNameFirstName = new ArrayList<>();
        for (int i = 0; i < personsById.size(); i++) {
            String pId = personsById.get(i);
            String a = PeopleManager.getPeopleLastNameById(pId);
            String b = PeopleManager.getPeopleFirstNameById(pId);
            personsByLastNameFirstName.add(a + " " + b);
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, personsByLastNameFirstName);
        woksite_people.setAdapter(adapter);
    }

    public void getAddress(double lat, double lng) {
        LocationHelper locationHelper = new LocationHelper(getActivity());
        address = locationHelper.getSimplifiedAddress(lat, lng);
        Toast.makeText(getActivity(), "latitude : " + lat + " longitude : " + lng, Toast.LENGTH_SHORT).show();
    }

    /**
     * To set date on TextView
     *
     * @param calendar
     */
    protected void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        worksite_vic.setText(dateFormat.format(calendar.getTime()));

    }
}
