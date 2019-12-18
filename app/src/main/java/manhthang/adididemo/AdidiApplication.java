package manhthang.adididemo;

import android.app.Application;

public class AdidiApplication extends Application {
    private static AdidiApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }
    public static AdidiApplication getInstance(){
        return instance;
    }

}
