package manhthang.adididemo.Adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import manhthang.adididemo.Common;
import manhthang.adididemo.Object.ImageAlbum;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ItemImageAlbumBinding;
/**
 * Created by manh thắng 98.
 */
public class ItemImageProductAdapter extends RecyclerView.Adapter<ItemImageProductAdapter.ItemRowHolder> {
    private ArrayList<ImageAlbum> images;

    public ItemImageProductAdapter(ArrayList<ImageAlbum> images) {
        this.images = images;
    }

    private OnItemClickedListener onItemClickedListener;



    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImageAlbumBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_image_album, parent, false);
        return new ItemImageProductAdapter.ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemRowHolder holder, final int position) {

        final ImageAlbum image = images.get(position);
        Bitmap bitmap = Common.returnBitmapFromStorage(image.getPath());
        if(bitmap != null){
            holder.binding.imvAlbum.setImageBitmap(bitmap);
        }
        holder.binding.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images.remove(image);
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemImageAlbumBinding binding;

        private ItemRowHolder(ItemImageAlbumBinding view) {
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
