package manhthang.adididemo.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import manhthang.adididemo.Data.Product;
import manhthang.adididemo.Fragment.FragmentDeliveryInstallation;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivityDeliveryBinding;

public class DeliveryActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCameraIdleListener {
    private ActivityDeliveryBinding binding;
    private GoogleMap mMap;
    private String addressUser;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delivery);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(DeliveryActivity.this);

        setUpView();
        setUpMapView();
        ActionToolbar();
        setUpOnClick();

    }

    private void setUpOnClick() {
    }

    private FusedLocationProviderClient mFusedLocationClient;
    private SupportMapFragment supportMapFragment;


    private void setUpMapView() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    private void setUpView() {
        fragment = new FragmentDeliveryInstallation();
        loadFragment(fragment);

    }

    private void ActionToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnCameraMoveStartedListener(this);
        mMap.setOnCameraIdleListener(this);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.setMyLocationEnabled(true);
        mFusedLocationClient.getLastLocation().addOnSuccessListener(DeliveryActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    zoomTarget(location.getLatitude(), location.getLongitude());
                    addMarker(location.getLatitude(), location.getLongitude());

                    //Gửi địa chỉ tới fragment thông qua eventbus
                    addressUser = getAddress(location.getLatitude(), location.getLongitude());
                    EventBus.getDefault().postSticky(addressUser);


                }
            }
        });


    }

    private void zoomTarget(double lat, double lng) {
        try {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(lat, lng)).zoom(18).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMarker(double lat, double lng) {
        try {
            mMap.clear();
           /* googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_location_on_blue_24dp)));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(
                DeliveryActivity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            Log.d("BBB", "getAddress: "+add);
            return add;
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public void onCameraMoveStarted(int reason) {

        if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
            binding.frameContainer.setVisibility(View.GONE);
        } else if (reason == GoogleMap.OnCameraMoveStartedListener
                .REASON_API_ANIMATION) {
            Toast.makeText(this, "The user tapped something on the map.",
                    Toast.LENGTH_SHORT).show();
        } else if (reason == GoogleMap.OnCameraMoveStartedListener
                .REASON_DEVELOPER_ANIMATION) {
            Toast.makeText(this, "The app moved the camera.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCameraIdle() {
        binding.frameContainer.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {

    }


}
