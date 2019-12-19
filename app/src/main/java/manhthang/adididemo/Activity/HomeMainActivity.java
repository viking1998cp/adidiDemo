package manhthang.adididemo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import manhthang.adididemo.Fragment.FragmentFunction;
import manhthang.adididemo.Fragment.FragmentHomePage;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivityHomeMainBinding;

/**
 * Created by manh thắng 98.
 */

public class HomeMainActivity extends AppCompatActivity {
    private int back =1;
    ActivityHomeMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(HomeMainActivity.this,R.layout.activity_home_main);

        loadFragment(new FragmentHomePage());
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(back != 1){
                        back =1;
                        fragment = new FragmentHomePage();
                        loadFragment(fragment);
                    }
                    return true;
                case R.id.navigation_listOrder:
                    if(back != 2){
                        back =2;
                        fragment = new FragmentHomePage();
                        loadFragment(fragment);
                    }
                    return true;
                case R.id.navigation_notification:
                    if(back != 3){
                        back =3;
                        fragment = new FragmentHomePage();
                        loadFragment(fragment);
                    }
                    return true;
                case R.id.navigation_function:
                    if(back != 3){
                        back =3;
                        fragment = new FragmentFunction();
                        loadFragment(fragment);
                    }
                    return true;
            }

            return false;
        }
    };

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
