package manhthang.adididemo.Data;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;

import manhthang.adididemo.Object.Voucher;
import manhthang.adididemo.R;

public class VoucherData {

    public static ArrayList<Voucher> getVoucherData(Context context){
        ArrayList<Voucher> vouchers = new ArrayList<>();

        Voucher voucher1 = new Voucher();
        voucher1.setIdImv(R.drawable.imv_title_voucher);
        voucher1.setTitle(context.getString(R.string.voucherName1));
        voucher1.setDetail(context.getString(R.string.voucherDetail1));
        vouchers.add(voucher1);
        vouchers.add(voucher1);
        vouchers.add(voucher1);
        return vouchers;
    }
}
