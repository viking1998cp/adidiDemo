package manhthang.adididemo.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.compat.AutocompleteFilter;

import java.util.ArrayList;

import manhthang.adididemo.Adapter.ItemLocationAdapter;
import manhthang.adididemo.Object.Location;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivitySearchLocationBinding;

/**
 * Created by manh thắng 98.
 */
public class SearchLocationActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener
        , GoogleApiClient.ConnectionCallbacks {

    private ActivitySearchLocationBinding binding;
    private ArrayList<Location> locations;
    private ItemLocationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_location);
        locations = new ArrayList<>();
        setUpOnClick();
        setUpListLocation();
    }


    GoogleApiClient mGoogleApiClient;
    private void setUpListLocation() {

        LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
                new LatLng(-40, -168), new LatLng(71, 136));

        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                                                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
                                                .setCountry("VN")
                                                .build();
        buildGoogleAPiClient();
        mGoogleApiClient.connect();

        mAdapter = new ItemLocationAdapter(SearchLocationActivity.this,mGoogleApiClient,
                LAT_LNG_BOUNDS, autocompleteFilter);
        binding.recycleResult.setHasFixedSize(true);
        binding.recycleResult.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recycleResult.setAdapter(mAdapter);
        mAdapter.setOnItemClickedListener(new ItemLocationAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {

            }
        });

    }

    private void setUpOnClick() {

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE
                   || actionId == EditorInfo.IME_ACTION_SEARCH
                   || event.getAction() == KeyEvent.ACTION_DOWN
                   || event.getAction() == KeyEvent.KEYCODE_ENTER){

                    if(mGoogleApiClient.isConnected()){
                        searchLocation(binding.etSearch.getText().toString().trim());
                    }

                }
                return false;
            }
        });

    }

    private void searchLocation(String keySearch) {
        mAdapter.getFilter().filter(keySearch);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("BBB", "onConnectionFailed: ");
    }



    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    protected synchronized void buildGoogleAPiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }

}
