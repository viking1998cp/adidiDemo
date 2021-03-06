package manhthang.adididemo.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import manhthang.adididemo.Activity.DeliveryActivity;
import manhthang.adididemo.Adapter.ItemServiceAdapter;
import manhthang.adididemo.Adapter.VoucherAdapter;
import manhthang.adididemo.Common;
import manhthang.adididemo.Data.ServiceData;
import manhthang.adididemo.Data.VoucherData;
import manhthang.adididemo.Dialog.ProductDialog;
import manhthang.adididemo.Object.Service;
import manhthang.adididemo.Object.Voucher;
import manhthang.adididemo.R;

import manhthang.adididemo.databinding.FragmentHomePageBinding;
/**
 * Created by manh thắng 98.
 */
public class FragmentHomePage extends Fragment {
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
        setUpDiscoverView();

        setScrollChangeColorBar();
        return binding.getRoot();
    }

    private void setUpDiscoverView() {
        ArrayList<Voucher> vouchers = VoucherData.getVoucherData(binding.getRoot().getContext());
        VoucherAdapter voucherAdapter = new VoucherAdapter(vouchers);

        binding.viewPagerDiscover.setAdapter(voucherAdapter);

        binding.indicatorDiscover.setViewPager(binding.viewPagerDiscover);
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
        final ArrayList<Service> services = ServiceData.getDataService();
        adapterService = new ItemServiceAdapter(services);

        adapterService.setOnItemClickedListener(new ItemServiceAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
                Intent intent ;
                switch (postion){
                    case 2:
                        intent = new Intent(getActivity(), DeliveryActivity.class);
                        startActivity(intent);
                }
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
