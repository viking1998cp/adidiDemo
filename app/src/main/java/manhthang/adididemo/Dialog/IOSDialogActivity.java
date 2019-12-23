package manhthang.adididemo.Dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import manhthang.adididemo.Activity.BaseNFCActivity;
import manhthang.adididemo.R;
import manhthang.adididemo.databinding.ActivityIosdialogBinding;

/**
 * Created by Varun John on August 2018.
 */
public class IOSDialogActivity extends BaseNFCActivity implements View.OnClickListener {

    public static void openActivity(Context context, IOSDialog iosDialog) {
        IOSDialogActivity.iosDialog = iosDialog;
        context.startActivity(new Intent(context, IOSDialogActivity.class));
    }

    private Context context;
    private static IOSDialog iosDialog;

    ActivityIosdialogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_iosdialog);
        this.setFinishOnTouchOutside(false);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        IOSDialog.iosDialogActivity = this;

        if (iosDialog.isEnableAnimation()) {
            binding.layoutDialog.setScaleX(1.3f);
            binding.layoutDialog.setScaleY(1.3f);
            binding.layoutContent.setAlpha(0);
            binding.layoutContent.animate().alpha(1f).setDuration(300).setInterpolator(new DecelerateInterpolator()).start();
            binding.layoutDialog.animate().scaleX(1f).scaleY(1f).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
        }

        if (iosDialog.getTitle() != null) {
            binding.textViewTitle.setText(iosDialog.getTitle());
        } else {
            binding.textViewTitle.setVisibility(View.GONE);
        }





        if (iosDialog.getNegativeButtonText() != null) {
            binding.textViewNegative.setText(iosDialog.getNegativeButtonText());
        } else {
            binding.textViewNegative.setText("");
            binding.layoutNegative.setVisibility(View.GONE);
        }

        binding.textViewNegative.setOnClickListener(this);
    }

    public void setTextViewTitle(String text) {
        binding.textViewTitle.setText(text);
    }


    public void setTextViewTitle(int text) {
        binding.textViewTitle.setText(text);
    }

    public void onOutsideClick(View view) {
        if (iosDialog.isCancelable()) {

            if (iosDialog.getCancelListener() != null) {
                iosDialog.getCancelListener().onClick(iosDialog);
            }

            onBackPressed();
        }
    }


    private boolean isAnimationExitDone;
//    @Override
//    public void onBackPressed() {
//
//        if (isAnimationExitDone || !iosDialog.isEnableAnimation()) {
////            super.onBackPressed();
//            overridePendingTransition(0, 0);
//            finish();
//            overridePendingTransition(0, 0);
//        }
//
//        layoutContent.animate().alpha(0f).setDuration(200).setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                isAnimationExitDone = true;
//                onBackPressed();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//            }
//        }).setInterpolator(new AccelerateInterpolator()).start();
//    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
       if (id == R.id.textViewNegative) {
            if (iosDialog.getNegativeClickListener() != null) {
                iosDialog.getNegativeClickListener().onClick(iosDialog);
            } else {
                dismiss();
            }
        } else {
        }
    }

    public void dismiss() {
//        onBackPressed();
        IOSDialogActivity.this.finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.alerter_slide_in_from_top, R.anim.alerter_slide_out_to_right);
    }
}
