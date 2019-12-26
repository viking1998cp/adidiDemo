package manhthang.adididemo.Dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import manhthang.adididemo.Activity.BaseNFCActivity;

import manhthang.adididemo.Adapter.ItemProductGroupAdapter;
import manhthang.adididemo.Adapter.ItemServiceAdapter;
import manhthang.adididemo.Data.Product;
import manhthang.adididemo.Object.ProductGroup;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivityProductDialogBinding;

/**
 * Created by Varun John on August 2018.
 */
public class ProductDialogActivity extends BaseNFCActivity implements View.OnClickListener {

    private ItemProductGroupAdapter adapter;
    public static void openActivity(Context context, ProductDialog productDialog) {
        ProductDialogActivity.productDialog = productDialog;
        context.startActivity(new Intent(context, ProductDialogActivity.class));

    }

    private ActivityProductDialogBinding binding;
    private static ProductDialog productDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_dialog);
        this.setFinishOnTouchOutside(false);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        ProductDialog.productDialogActivity = this;

        if (productDialog.isEnableAnimation()) {
            binding.layoutDialog.setScaleX(1.3f);
            binding.layoutDialog.setScaleY(1.3f);
            binding.layoutContent.setAlpha(0);
            binding.layoutContent.animate().alpha(1f).setDuration(300).setInterpolator(new DecelerateInterpolator()).start();
            binding.layoutDialog.animate().scaleX(1f).scaleY(1f).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
        }

        if (productDialog.getTitle() != null) {
            binding.textViewTitle.setText(productDialog.getTitle());
        } else {
            binding.textViewTitle.setVisibility(View.GONE);
        }

        if (productDialog.getNegativeButtonText() != null) {
            binding.textViewNegative.setText(productDialog.getNegativeButtonText());
        } else {
            binding.textViewNegative.setText("");
            binding.layoutNegative.setVisibility(View.GONE);
        }

        binding.textViewNegative.setOnClickListener(this);

        final ArrayList<ProductGroup> products = Product.getProductGroup();
        adapter = new ItemProductGroupAdapter(products);
        adapter.setOnItemClickedListener(new ItemProductGroupAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {

            }
        });

        binding.recycleProduct.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.recycleProduct.setAdapter(adapter);


    }

    public void setTextViewTitle(String text) {
        binding.textViewTitle.setText(text);
    }


    public void setTextViewTitle(int text) {
        binding.textViewTitle.setText(text);
    }

    public void onOutsideClick(View view) {
        if (productDialog.isCancelable()) {
            if (productDialog.getCancelListener() != null) {
                productDialog.getCancelListener().onClick(productDialog);
            }
            onBackPressed();
        }
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.textViewNegative) {
            if (productDialog.getNegativeClickListener() != null) {
                productDialog.getNegativeClickListener().onClick(productDialog);
                Log.d("BBB", "onClick: BBB");
            } else {
                dismiss();
            }
        } else {
        }
    }

    public ProductGroup getProductSelect() {
        return adapter.getProductCheck();
    }



    public void dismiss() {
//        onBackPressed();
        ProductDialogActivity.this.finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.alerter_slide_in_from_top, R.anim.alerter_slide_out_to_right);
    }
}
