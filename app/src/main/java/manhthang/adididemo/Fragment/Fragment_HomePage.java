package manhthang.adididemo.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;

import manhthang.adididemo.Adapter.ItemServiceAdapter;
import manhthang.adididemo.Adapter.VoucherAdapter;
import manhthang.adididemo.Common;
import manhthang.adididemo.Data.ServiceData;
import manhthang.adididemo.Data.VoucherData;
import manhthang.adididemo.Object.ServiceObject;
import manhthang.adididemo.Object.Voucher;
import manhthang.adididemo.R;

import manhthang.adididemo.databinding.FragmentHomePageBinding;

public class Fragment_HomePage extends Fragment {
    private FragmentHomePageBinding binding;
    private ItemServiceAdapter adapterService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false);
        Common.setStatusBar(R.color.colorOrangeYellow ,getActivity());

        ActionToolbar();
        setUpServiceView();
        setUpVoucherView();

        setScrollChangeColorBar();
        return binding.getRoot();
    }


    //Scroll xuống thay màu toolbar và statusbar , em chưa custom cái toolbar
    private void setScrollChangeColorBar() {
        binding.scrollHomePage.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {

                    binding.toolbar.setBackgroundResource(R.color.colorWhite);
                    Common.setStatusBar(R.color.colorBlack,getActivity());

                }
                if (scrollY < oldScrollY) {

                }

                if (scrollY == 0) {
                    binding.toolbar.setBackgroundResource(android.R.color.transparent);
                    Common.setStatusBar(R.color.colorOrangeYellow ,getActivity());
                }

                if (scrollY == ( v.getMeasuredHeight() - v.getChildAt(0).getMeasuredHeight() )) {

                }
            }
        });
    }


    private void setUpVoucherView() {
        ArrayList<Voucher> vouchers = VoucherData.getVoucherData(binding.getRoot().getContext());
        VoucherAdapter voucherAdapter = new VoucherAdapter(vouchers);

        binding.viewPagerVoucher.setAdapter(voucherAdapter);

        binding.indicatorVoucher.setViewPager(binding.viewPagerVoucher);
    }

    private void setUpServiceView() {
        binding.recycleService.setHasFixedSize(true);
        binding.recycleService.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(), 3));
        ArrayList<ServiceObject> serviceObjects = ServiceData.getDataService();
        adapterService = new ItemServiceAdapter( serviceObjects);

        adapterService.setOnItemClickedListener(new ItemServiceAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {

            }
        });

        binding.recycleService.setAdapter(adapterService);
    }

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
}
