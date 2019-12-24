package manhthang.adididemo.Dialog;

import android.content.Context;
import android.os.Handler;
import android.view.View;


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
    private View view;

    static ProductDialogActivity iosDialogActivity;

    private Context context;

    private ProductDialog(Context context) {
        this.context = context;
    }

    public void show() {
        ProductDialogActivity.openActivity(context, this);
    }

    public void dismiss() {
        if (iosDialogActivity != null) {
            iosDialogActivity.dismiss();

        }
    }


    public void setTitle(final String title) {
        this.title=title;
        if(iosDialogActivity!=null){
            iosDialogActivity.setTextViewTitle(this.title);
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
        if(iosDialogActivity!=null){
            iosDialogActivity.setTextViewTitle(this.title);
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

    public static class Builder {

        private ProductDialog iosDialog;
        private Context context;

        public Builder(Context context) {
            this.context = context;
            iosDialog = new ProductDialog(context);

        }

        public ProductDialog build() {
            return iosDialog;
        }

        public Builder title(String title) {
            iosDialog.title = title;
            return this;
        }

        public Builder title(int titleResourceId) {
            iosDialog.title = context.getString(titleResourceId);
            return this;
        }

        public Builder negativeButtonText(String negativeButtonText) {
            iosDialog.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder negativeButtonText(int negativeButtonTextResourceId) {
            iosDialog.negativeButtonText = context.getString(negativeButtonTextResourceId);
            return this;
        }

        public Builder negativeClickListener(Listener negativeClickListener) {
            iosDialog.negativeClickListener = negativeClickListener;
            return this;
        }

        public Builder cancelListener(Listener cancelListener) {
            iosDialog.cancelListener = cancelListener;
            return this;
        }

        public Builder enableAnimation(boolean enableAnimation) {
            iosDialog.enableAnimation = enableAnimation;
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            iosDialog.cancelable = cancelable;
            return this;
        }
    }

}
