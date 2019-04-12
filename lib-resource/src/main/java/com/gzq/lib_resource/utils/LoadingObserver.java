package com.gzq.lib_resource.utils;

import com.gzq.lib_core.http.exception.ApiException;
import com.gzq.lib_core.http.observer.BaseObserver;
import com.gzq.lib_resource.R;
import com.gzq.lib_resource.dialog.FDialog;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;


public abstract class LoadingObserver<T> extends BaseObserver<T> {

    private FragmentManager fragmentManager = null;
    private FDialog fd;

    public LoadingObserver(LifecycleOwner owner) {
        if (owner instanceof FragmentActivity) {
            fragmentManager = ((FragmentActivity) owner).getSupportFragmentManager();
        } else if (owner instanceof Fragment) {
            fragmentManager = ((Fragment) owner).getFragmentManager();
        }
    }

    @Override
    protected void onNetError() {
        com.gzq.lib_core.toast.T.show("当前无网络，请检查网络情况");
    }

    @Override
    protected void onError(ApiException ex) {
        hideLoading();
        com.gzq.lib_core.toast.T.show(ex.message + ":" + ex.code);
    }

    @Override
    public abstract void onNext(T t);


    @Override
    public void onComplete() {
        hideLoading();
    }

    private void showLoading() {
        if (fragmentManager != null) {
            fd = FDialog.build()
                    .setLayoutId(R.layout.dialog_layout_loading)
                    .setOutCancel(false)
                    .show(fragmentManager);
        }
    }

    private void hideLoading() {
        if (fd != null) {
            fd.dismiss();
        }
    }
}
