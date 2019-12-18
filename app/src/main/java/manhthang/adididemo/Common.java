package manhthang.adididemo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Common {
    public final static String PHONENUMBER = "phonenumber";
    public final static String PASSWORD = "password";
    public final static String STATUSLOGIN = "statuslogin";

    public static void ShowToast(Context context, String toast){
        Toast.makeText(context,toast,Toast.LENGTH_SHORT).show();
    }

    public static String twoWordOneLine(String text){
        String[] texts = text.split(" ");
        String value ="";
        Log.d("BBB", "twoWordOneLine: "+texts.length);
        if(texts.length<=2){
            return text;
        }else {
            for(int i=0;i<texts.length;i++){
                if(i%2!=0){
                    texts[i] += "\n";
                }
                value += texts[i]+" ";
            }
            value.trim();

            return value;
        }
    }

    public static Spanned stripHtml(String html) {
        if (!TextUtils.isEmpty(html)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
            } else {
                return Html.fromHtml(html);
            }
        }
        return null;
    }

    public static void setStatusBar(int color, Activity activity){

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(color));
        }
    }

}
