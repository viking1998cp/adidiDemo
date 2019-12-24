package manhthang.adididemo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import manhthang.adididemo.Object.ProductGroup;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ItemProductGroupBinding;
import manhthang.adididemo.databinding.ItemServiceBinding;

public class ItemProductGroupAdapter extends RecyclerView.Adapter<ItemProductGroupAdapter.ItemRowHolder> {

    ArrayList<ProductGroup> products;

    public ItemProductGroupAdapter(ArrayList<ProductGroup> products) {
        this.products = products;
    }

    private ItemServiceAdapter.OnItemClickedListener onItemClickedListener;

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductGroupBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product_group, parent, false);
        return new ItemProductGroupAdapter.ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemRowHolder holder, final int position) {
        final ProductGroup productGroup = products.get(position);
        holder.binding.rdProduct.setText(productGroup.getNameGroup());

        if(productGroup.isChecked()) holder.binding.rdProduct.setChecked(true);
        else holder.binding.rdProduct.setChecked(false);

        holder.binding.rdProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setChecked(position);
                notifyDataSetChanged();
            }
        });

    }

    public void setChecked(int position){
        products.get(position).setChecked(true);
        for(int i=0;i<products.size();i++){
            if(i!=position){
                products.get(i).setChecked(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemProductGroupBinding binding;

        private ItemRowHolder(ItemProductGroupBinding view) {
            super(view.getRoot());
            binding = view;
            view.getRoot().setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onItemClickedListener.onItemClick(getAdapterPosition(), v);
        }
    }



    public void setOnItemClickedListener(ItemServiceAdapter.OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
