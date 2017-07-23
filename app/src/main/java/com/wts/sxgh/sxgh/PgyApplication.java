package com.wts.sxgh.sxgh;

import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by wts on 2016/12/16.
 */

public class PgyApplication extends Application {
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        PgyCrashManager.register(this);
    }
}
