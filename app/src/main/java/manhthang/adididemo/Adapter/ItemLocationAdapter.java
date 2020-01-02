package manhthang.adididemo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import manhthang.adididemo.Object.Location;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ItemLocationBinding;
/**
 * Created by manh thắng 98.
 */
public class ItemLocationAdapter extends RecyclerView.Adapter<ItemLocationAdapter.ItemRowHolder>{


    private OnItemClickedListener onItemClickedListener;
    private ArrayList<Location> locations;

    public ItemLocationAdapter(ArrayList<Location> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLocationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_location, parent, false);
        return new ItemLocationAdapter.ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, int position) {
        Location location = locations.get(position);
        holder.binding.tvName.setText(location.getNameLocation());
        holder.binding.tvNameDetail.setText(location.getNameDetailLocation());
    }

    @Override
    public int getItemCount() {
        return locations.size();
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

    public interface OnItemClickedListener {
        void onItemClick(int postion, View v);
    }


    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
