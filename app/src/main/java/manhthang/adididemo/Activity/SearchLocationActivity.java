package manhthang.adididemo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

import manhthang.adididemo.Adapter.ItemLocationAdapter;
import manhthang.adididemo.Object.Location;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivitySearchLocationBinding;

/**
 * Created by manh thắng 98.
 */
public class SearchLocationActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private ActivitySearchLocationBinding binding;
    private ArrayList<Location> locations;
    private ItemLocationAdapter adapterLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_location);
        locations = new ArrayList<>();
        setUpOnClick();
        setUpListLocation();
    }

    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));
    GoogleApiClient mGoogleApiClient;
    private void setUpListLocation() {

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        binding.recycleResult.setHasFixedSize(true);
        binding.recycleResult.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(), 1));
        adapterLocation = new ItemLocationAdapter(locations);
        binding.recycleResult.setAdapter(adapterLocation);
    }

    private Handler m_handler = new Handler();
    private Runnable m_runnable;

    private void setUpOnClick() {
        m_runnable = new Runnable() {
            @Override
            public void run() {
                searchLocation(binding.etSearch.getText().toString());
                adapterLocation.notifyDataSetChanged();
            }
        };

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE
                   || actionId == EditorInfo.IME_ACTION_SEARCH
                   || event.getAction() == KeyEvent.ACTION_DOWN
                   || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    searchLocation(binding.etSearch.getText().toString().trim());

                }
                return false;
            }
        });
    }

    private void searchLocation(String keySearch) {
        Geocoder geocoder = new Geocoder(this);
        List<Address> list = new ArrayList<>();
        locations.clear();
        try {
            list = geocoder.getFromLocationName(keySearch, 5);
        } catch (Exception e) {
            Log.d("BBB", "searchLocation: " + e.getMessage());
        }
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Location location = new Location();
                location.setLat(list.get(i).getLatitude());
                location.setLogt(list.get(i).getLongitude());
                location.setNameLocation(list.get(i).getCountryName());
                location.setNameDetailLocation(list.get(i).getAddressLine(0));
                locations.add(location);
                Log.d("BBB", "searchLocation: "+ location.getNameDetailLocation());
            }
        }


        adapterLocation.notifyDataSetChanged();

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
