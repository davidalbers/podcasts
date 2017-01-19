package com.dalbers.podcastexplorer;

import android.app.Application;

import com.dalbers.podcastexplorer.dagger.DaggerDataComponent;
import com.dalbers.podcastexplorer.dagger.DataComponent;
import com.dalbers.podcastexplorer.dagger.DataModule;

/**
 * Created by davidalbers on 1/6/17.
 */

public class BaseApplication extends Application {

    private DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        dataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule())
                .build();

    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }
}
