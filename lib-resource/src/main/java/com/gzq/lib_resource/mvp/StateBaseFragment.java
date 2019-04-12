package com.gzq.lib_resource.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gzq.lib_core.utils.NetworkUtils;
import com.gzq.lib_resource.mvp.base.BaseFragment;
import com.gzq.lib_resource.mvp.base.IPresenter;
import com.gzq.lib_resource.state_page.EmptyPage;
import com.gzq.lib_resource.state_page.ErrorPage;
import com.gzq.lib_resource.state_page.LoadingPage;
import com.gzq.lib_resource.state_page.NetErrorPage;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.core.Transport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * copyright：杭州国辰迈联机器人科技有限公司
 * version: V1.3.0
 * created on 2018/10/31 10:24
 * created by: gzq
 * description: 二次封装的带状态页面的BaseFragment
 */
public abstract class StateBaseFragment<P extends IPresenter> extends BaseFragment {
    protected LoadService mStateView;
    private View mView;

    @Override
    public P getP() {
        return (P) super.getP();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = layoutId(savedInstanceState);
        if (layoutId <= 0) {
            throw new IllegalArgumentException("layout is null");
        }
        if (mView == null) {
            mView = inflater.inflate(layoutId, container, false);
            //初始化状态页面
            Object rootView = placeView();
            if (rootView == null) {
                rootView = mView;
            }
            initStateView(rootView);
            //初始化基本参数
            initParams(getArguments());
            //初始化Presenter
            setP(obtainPresenter());
            if (getP() != null) {
                getLifecycle().addObserver(getP());
            }
            //初始化控件id
            initView(mView);
            afterInitView();
        }
        //判断网络是否可用
        if (!NetworkUtils.isAvailable()) {
            showNetError();
        }
        return mStateView.getLoadLayout();
    }

    private void initStateView(Object rootView) {
        mStateView = LoadSir.getDefault().register(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                reloadData(v);
            }
        }).setCallBack(EmptyPage.class, new Transport() {
            @Override
            public void order(Context context, View view) {
                customEmptyPage(context, view);
            }
        }).setCallBack(ErrorPage.class, new Transport() {
            @Override
            public void order(Context context, View view) {
                customErrorPage(context, view);
            }
        }).setCallBack(LoadingPage.class, new Transport() {
            @Override
            public void order(Context context, View view) {
                customLoadingPage(context, view);
            }
        }).setCallBack(NetErrorPage.class, new Transport() {
            @Override
            public void order(Context context, View view) {
                customNetErrorPage(context, view);
            }
        });
    }

    protected View placeView() {
        return null;
    }

    protected void customEmptyPage(Context context, View view) {

    }

    protected void customErrorPage(Context context, View view) {

    }

    protected void customLoadingPage(Context context, View view) {

    }

    protected void customNetErrorPage(Context context, View view) {

    }

    /**
     * 重试
     *
     * @param view
     */
    public void reloadData(View view) {

    }

    public void showLoading() {
        if (mStateView != null) {
            mStateView.showCallback(LoadingPage.class);
        }
    }

    public void showEmpty() {
        if (mStateView != null) {
            mStateView.showCallback(EmptyPage.class);
        }
    }

    public void showError() {
        if (mStateView != null) {
            mStateView.showCallback(ErrorPage.class);
        }
    }

    public void showSuccess() {
        if (mStateView != null) {
            mStateView.showSuccess();
        }
    }

    public void showNetError() {
        if (mStateView != null) {
            mStateView.showCallback(NetErrorPage.class);
        }
    }
}
