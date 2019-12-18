package manhthang.adididemo.Adapter;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import manhthang.adididemo.Common;
import manhthang.adididemo.Object.Voucher;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ItemVoucherBinding;

public class VoucherAdapter extends PagerAdapter {

    private ArrayList<Voucher> vouchers;
    private ItemClickListener itemClickListener;
    public VoucherAdapter(ArrayList<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    @Override
    public int getCount() {
        return vouchers.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemVoucherBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.item_voucher, container, false);
        binding.getRoot().setTag(position);

        Voucher voucher = vouchers.get(position);
        binding.imvService.setImageResource(voucher.getIdImv());
        binding.nameVoucher.setText(Common.stripHtml(voucher.getTitle()));
        binding.voucherDetail.setText(Common.stripHtml(voucher.getDetail()));
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
    public interface ItemClickListener {
        public void onItemClick(int position);
    }
    @Override
    public float getPageWidth(int position) {
        return(0.8f);
    }
}
