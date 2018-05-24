package com.example.rvadam.pfe.WorkSitePhotoGallery;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.rvadam.pfe.R;
import com.example.rvadam.pfe.Utils.ListOfPhotosSingletonManager;
import com.example.rvadam.pfe.Utils.WorkSitesManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class TabsOfPhotosActivity extends AppCompatActivity {

    public static final String ID_WORKSITE ="id worksite" ;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    String idWorkSite;

    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    public static final String TAG = "TabsOfPhotosActivity" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_of_photos);


        //We retrieve the worksite ID and update of this id in each photos
        idWorkSite="-LBw-rNjtmo9G70LUU2Z";
        ListOfPhotosSingletonManager.updateWorkSiteIdListOfPhotosSingleton(idWorkSite);
        //String nameWorkSite= WorkSitesManager.getWorkSiteById(idWorkSite).getName();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //Firebase authentication to make FB storage work
        FirebaseUser user = mAuth.getCurrentUser();
        //Log.i(TAG,"user current "+user.toString());
        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously();
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new CourseAccessFragment(), getResources().getString(R.string.tab_course_access));
        adapter.addFrag(new GeneralViewFragment(),getResources().getString(R.string.tab_general_view_access ));
        adapter.addFrag(new TechnicalEquipmentsFragment(), getResources().getString(R.string.tab_technical_equipments));
        adapter.addFrag(new MaltAdductionsFragment(), getResources().getString(R.string.tab_malt_adductions));
        adapter.addFrag(new SecurityFragment(), getResources().getString(R.string.tab_security));
        viewPager.setAdapter(adapter);
    }
    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do your stuff
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e(TAG, "signInAnonymously:FAILURE", exception);
                    }
                });
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
