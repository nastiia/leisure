package by.bsuir.leisure;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by nastia on 28.05.2016.
 */
public class LeisureApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
