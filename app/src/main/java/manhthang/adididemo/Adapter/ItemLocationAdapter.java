package manhthang.adididemo.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.compat.AutocompleteFilter;
import com.google.android.libraries.places.compat.AutocompletePrediction;
import com.google.android.libraries.places.compat.AutocompletePredictionBufferResponse;
import com.google.android.libraries.places.compat.GeoDataClient;
import com.google.android.libraries.places.compat.Places;

import java.util.ArrayList;
import java.util.Iterator;

import manhthang.adididemo.Object.Location;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ItemLocationBinding;
/**
 * Created by manh thắng 98.
 */
public class ItemLocationAdapter extends RecyclerView.Adapter<ItemLocationAdapter.ItemRowHolder> implements Filterable {

    Context mContext;

    private static final String TAG = "ItemLocationAdapter";
    private static final CharacterStyle STYLE_BOLD = new StyleSpan(Typeface.BOLD);
    ArrayList<Location> mResultList;

    private GoogleApiClient mGoogleApiClient;

    private LatLngBounds mBounds;

    private int layout;

    private AutocompleteFilter mPlaceFilter;

    private OnItemClickedListener onItemClickedListener;


    public ItemLocationAdapter(Context context, GoogleApiClient googleApiClient,
                               LatLngBounds bounds, AutocompleteFilter filter) {
        this.mContext = context;
        mGoogleApiClient = googleApiClient;
        mBounds = bounds;
        mPlaceFilter = filter;

    }

    /*
    Clear List items
     */
    public void clearList() {
        if (mResultList != null && mResultList.size() > 0) {
            mResultList.clear();
        }
    }


    /**
     * Sets the bounds for all subsequent queries.
     */
    public void setBounds(LatLngBounds bounds) {
        mBounds = bounds;
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                // Skip the autocomplete query if no constraints are given.
                if (constraint != null) {
                    // Query the autocomplete API for the (constraint) search string.
                    mResultList = getAutocomplete(constraint);
                    if (mResultList != null) {
                        // The API successfully returned results.
                        results.values = mResultList;
                        results.count = mResultList.size();
                        Log.d("BBB", "performFilteringT: " + results.count);
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    // The API returned at least one result, update the data.
                    notifyDataSetChanged();
                } else {
                    // The API did not return any results, invalidate the data set.
                    //notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    ArrayList resultList;

    private ArrayList<Location> getAutocomplete(CharSequence constraint) {
        if (mGoogleApiClient.isConnected()) {
            GeoDataClient mGeoDataClient;
            mGeoDataClient = Places.getGeoDataClient(mContext, null);
            // Submit the query to the autocomplete API and retrieve a PendingResult that will
            // contain the results when the query completes.
            final Task<AutocompletePredictionBufferResponse> results =
                    mGeoDataClient.getAutocompletePredictions(constraint.toString(),
                            mBounds, mPlaceFilter);

            // This method should have been called off the main UI thread. Block and wait for at most 60s
            // for a result from the API.

            // Confirm that the query completed successfully, otherwise return null
            resultList = new ArrayList<>();
            results.addOnSuccessListener(new OnSuccessListener<AutocompletePredictionBufferResponse>() {
                @Override
                public void onSuccess(AutocompletePredictionBufferResponse autocompletePredictions) {
                    Iterator<AutocompletePrediction> iterator = results.getResult().iterator();

                    while (iterator.hasNext()) {
                        AutocompletePrediction prediction = iterator.next();
                        // Get the details of this prediction and copy it into a new PlaceAutocomplete object.
                        Location location = new Location();
                        location.setNameLocation(prediction.getPrimaryText(STYLE_BOLD).toString());
                        location.setNameDetailLocation(prediction.getSecondaryText(STYLE_BOLD).toString());

                        resultList.add(location);
                        Log.d("BBB", "onSuccess: " + prediction.getSecondaryText(STYLE_BOLD));

                    }
                    notifyDataSetChanged();
                    Log.d("BBB", "getAutocomplete: " + resultList.size());

                }
            });


            return resultList;
        }
        Log.e("BBB", "Google API client is not connected for autocomplete query.");
        return null;
    }

    @NonNull
    @Override
    public ItemLocationAdapter.ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLocationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_location, parent, false);
        return new ItemLocationAdapter.ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, int position) {

        Location location = getItem(position);
        holder.binding.tvName.setText(location.getNameLocation());
        holder.binding.tvNameDetail.setText(location.getNameDetailLocation());
    }


    @Override
    public int getItemCount() {
        if (mResultList != null)
            return mResultList.size();

        else
            return 0;
    }

    public Location getItem(int position) {
        return mResultList.get(position);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemLocationBinding binding;

        private ItemRowHolder(ItemLocationBinding view) {
            super(view.getRoot());
            binding = view;
            view.getRoot().setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickedListener.onItemClick(getAdapterPosition(), v);
        }


    }

    /**
     * Holder for Places Geo Data Autocomplete API results.
     */

    public interface OnItemClickedListener {
        void onItemClick(int postion, View v);
    }


    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}