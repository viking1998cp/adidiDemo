package manhthang.adididemo.Dialog;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import manhthang.adididemo.Object.ProductGroup;


/**
 * Created by Varun John on August 2018.
 */
public class ProductDialog {

    public interface Listener {
        void onClick(ProductDialog iosDialog);
    }
    Handler mHandler=new Handler();
    private static final int DELAY = 20;
    private String title;
    private String negativeButtonText;
    private Listener negativeClickListener;
    private Listener cancelListener;
    private boolean enableAnimation = true;
    private boolean cancelable = true;
    private ProductGroup product;
    private View view;

    static ProductDialogActivity productDialogActivity;

    private Context context;

    private ProductDialog(Context context) {
        this.context = context;
    }

    public void show() {
        ProductDialogActivity.openActivity(context, this);
    }

    public void dismiss() {
        if (productDialogActivity != null) {
            productDialogActivity.dismiss();

        }
    }


    public void setTitle(final String title) {
        this.title=title;
        if(productDialogActivity!=null){
            productDialogActivity.setTextViewTitle(this.title);
        }else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setTitle(title);
                }
            }, DELAY);
        }
    }
    public void setTitle(final int title) {
        this.title=context.getString(title);
        if(productDialogActivity!=null){
            productDialogActivity.setTextViewTitle(this.title);
        }else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setTitle(title);
                }
            }, DELAY);
        }
    }

    public View getView() {
        return view;
    }

    public String getTitle() {
        return title;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public Listener getNegativeClickListener() {
        return negativeClickListener;
    }

    public Listener getCancelListener() {
        return cancelListener;
    }

    public boolean isEnableAnimation() {
        return enableAnimation;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public ProductGroup getProduct() {
        return productDialogActivity.getProductSelect();
    }


    public static class Builder {

        private ProductDialog productDialog;
        private Context context;

        public Builder(Context context) {
            this.context = context;
            productDialog = new ProductDialog(context);

        }

        public ProductDialog build() {
            return productDialog;
        }

        public Builder title(String title) {
            productDialog.title = title;
            return this;
        }

        public Builder title(int titleResourceId) {
            productDialog.title = context.getString(titleResourceId);
            return this;
        }

        public Builder negativeButtonText(String negativeButtonText) {
            productDialog.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder negativeButtonText(int negativeButtonTextResourceId) {
            productDialog.negativeButtonText = context.getString(negativeButtonTextResourceId);
            return this;
        }

        public Builder negativeClickListener(Listener negativeClickListener) {
            productDialog.negativeClickListener = negativeClickListener;
            return this;
        }

        public Builder cancelListener(Listener cancelListener) {
            productDialog.cancelListener = cancelListener;
            return this;
        }

        public Builder enableAnimation(boolean enableAnimation) {
            productDialog.enableAnimation = enableAnimation;
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            productDialog.cancelable = cancelable;
            return this;
        }

        public Builder product(ProductGroup product) {
            productDialog.product = product;
            return this;
        }
    }

}
