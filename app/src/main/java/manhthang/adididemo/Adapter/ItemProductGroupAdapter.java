package manhthang.adididemo.Adapter;

import android.util.Log;
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
/**
 * Created by manh thắng 98.
 */
public class ItemProductGroupAdapter extends RecyclerView.Adapter<ItemProductGroupAdapter.ItemRowHolder> {

    private ArrayList<ProductGroup> products;
    private int mCheckedPostion ;
    private ProductGroup productCheck  ;
    private boolean isChecked = false;
    public ItemProductGroupAdapter(ArrayList<ProductGroup> products) {
        this.products = products;

    }

    private OnItemClickedListener onItemClickedListener;

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
        if(mCheckedPostion == position ) holder.binding.rdProduct.setChecked(true);
        else holder.binding.rdProduct.setChecked(false);

        if(holder.binding.rdProduct.isChecked()){
            productCheck = productGroup;
            Log.d("BBB", "onBindViewHolder: "+productCheck.getNameGroup());
        }
        //click to one item , other item un check
        holder.binding.rdProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChecked) {
                    notifyItemChanged(mCheckedPostion);
                    mCheckedPostion = position;
                    notifyItemChanged(mCheckedPostion);
                    isChecked = true;

                } else {
                    notifyItemChanged(mCheckedPostion);
                    mCheckedPostion = position;
                    notifyItemChanged(position);
                    isChecked = false;
                }
            }
        });
        Log.d("BBB", "onBindViewHolder: "+isChecked);


    }

    public ProductGroup getProductCheck() {
        return productCheck;
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

    public interface OnItemClickedListener {
        void onItemClick(int postion, View v);
    }


    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
