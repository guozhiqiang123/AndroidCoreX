package com.gzq.lib_core.base.quality;

import android.app.Activity;
import android.os.Bundle;

import com.gzq.lib_core.base.delegate.ActivityLifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class QualityActivity implements ActivityLifecycle {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onDestroy(Activity activity) {
        LeakCanaryUtil.getInstance().watch(activity);
    }
}
