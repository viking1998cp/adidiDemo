package manhthang.adididemo.Adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import manhthang.adididemo.Common;
import manhthang.adididemo.Object.ServiceObject;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ItemServiceBinding;

public class ItemServiceAdapter extends RecyclerView.Adapter<ItemServiceAdapter.ItemRowHolder> {

    private OnItemClickedListener onItemClickedListener;

    private ArrayList<ServiceObject> arrayList;

    public ItemServiceAdapter(ArrayList<ServiceObject> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemServiceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_service, parent, false);
        return new ItemServiceAdapter.ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, int position) {

        ServiceObject service = arrayList.get(position);
        holder.binding.imvService.setImageResource(service.getIdImv());
        holder.binding.nameService.setText(Common.twoWordOneLine(service.getTitle()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemServiceBinding binding;

        private ItemRowHolder(ItemServiceBinding view) {
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
