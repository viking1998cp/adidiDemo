package manhthang.adididemo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static double windowWidth;
    public static double windowHeight;

    public static void ShowToast(Context context, String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }


    //Đưa từ văn bản html về dạng thường
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

    //Thay đổi màu statusbar
    public static void setStatusBar(int color, Activity activity) {

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    // check trang thai fake GPS version >23
    private static List<String> getListAppFake() {
        List<String> listAppFakeGPS = new ArrayList<>();

        String arrayList = SharedPrefs.getInstance().get(SharedPrefs.ListAppFake, String.class);
        try {
            JSONArray listAppface = new JSONArray(arrayList);
            for (int i = 0; i < listAppface.length(); i++) {
                String temp;
                JSONObject job = listAppface.getJSONObject(i);
                temp = job.getString("packetname");
                listAppFakeGPS.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return listAppFakeGPS;
    }

    public static String checkFakeGPS() {
        try {
            List<String> listAppProduct = getListAppFake();
            PackageManager pm = AdidiApplication.getInstance().getPackageManager();
            List<ApplicationInfo> packages = null;
            try {
                packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
            } catch (Exception e) {
                //e.printStackTrace();
            }
            PackageInfo packageInfo;
            String[] requestedPermissions;
            String packetNameface = "";
            StringBuilder packetNameFakes = new StringBuilder();
            if (packages != null) {
                for (ApplicationInfo applicationInfo : packages) {
                    try {
                        //String currAppName = pm.getApplicationLabel(applicationInfo).toString();
                        String srcDir = applicationInfo.sourceDir;
                        if (srcDir.startsWith("/data/app/") && pm.getLaunchIntentForPackage(applicationInfo.packageName) != null) {
                            packageInfo = pm.getPackageInfo(applicationInfo.packageName,
                                    PackageManager.GET_PERMISSIONS);
                            // Get Permissions
                            requestedPermissions = packageInfo.requestedPermissions;
                            if (requestedPermissions != null) {
                                for (String requestedPermission : requestedPermissions) {
                                    if (requestedPermission
                                            .equals("android.permission.ACCESS_MOCK_LOCATION")
                                            && !applicationInfo.packageName.equals(AdidiApplication.getInstance().getPackageName())) {
                                        packetNameface = applicationInfo.packageName;
                                        /*   if (Build.VERSION.SDK_INT >= 24) {*/
                                        Boolean fakeapp = true;
                                        try {
                                            for (int l = 0; l < listAppProduct.size(); l++) {
                                                if (packetNameface.equals(listAppProduct.get(l))) {
                                                    fakeapp = false;
                                                }
                                            }
                                        } catch (Exception e) {
                                            //e.printStackTrace();
                                        }
                                        if (fakeapp) {
                                            packetNameFakes.append(packetNameface).append(",");
                                        }

                                    }
                                }
                            }
                        }

                    } catch (PackageManager.NameNotFoundException e) {
                        // Log.e("Got exception ", e.getMessage());
                        return "";
                    }
                }
            }
            if (!packetNameFakes.toString().equals("")) {
                packetNameFakes = new StringBuilder(packetNameFakes.substring(0, packetNameFakes.length() - 1));
                //MD5.showLog("tungdaisFakeGPS11: "+packetNameFakes);
            }
            return packetNameFakes.toString();
        } catch (Exception e) {
            return "";
            //e.printStackTrace();
        }
    }

    public static String byteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String out = "";
        for (j = inarray.length - 1; j >= 0; j--) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }
}
