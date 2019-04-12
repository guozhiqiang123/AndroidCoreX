package com.gzq.lib_core.base.quality;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.gzq.lib_core.base.delegate.FragmentLifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QualityFragment implements FragmentLifecycle {
    @Override
    public void onAttach(@NonNull Context context) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onCreateView(@Nullable View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreate(@Nullable Bundle savedInstanceState) {

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
    public void onDestroyView() {

    }

    @Override
    public void onDestroy(Fragment fragment) {
        LeakCanaryUtil.getInstance().watch(fragment);
    }

    @Override
    public void onDetach() {

    }

    @Override
    public boolean isAdded() {
        return false;
    }
}
