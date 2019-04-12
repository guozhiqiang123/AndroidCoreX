package com.gzq.lib_resource.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.yokeyword.fragmentation.SupportFragment;


public abstract class BaseFragment<P extends IPresenter>
        extends SupportFragment implements IView {
    private View mView;
    private Activity mActivity;
    private Context mContext;
    private P mPresenter;

    public P getP() {
        return mPresenter;
    }

    protected void setP(P presenter) {
        mPresenter = presenter;
    }

    public View getRootView() {
        return mView;
    }

    public Activity getmActivity() {
        return mActivity;
    }

    public Context getmContext() {
        return mContext;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
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
            initParams(getArguments());
            mPresenter = obtainPresenter();
            if (mPresenter != null) {
                getLifecycle().addObserver(mPresenter);
            }
            initView(mView);
        }
        afterInitView();
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mView != null) {
            ((ViewGroup) mView.getParent()).removeView(mView);
            mView = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView = null;
        mActivity = null;
        mContext = null;
        mPresenter = null;
    }

    public abstract int layoutId(Bundle savedInstanceState);

    public abstract void initParams(Bundle bundle);

    public abstract void initView(View view);

    public abstract P obtainPresenter();

    public abstract void afterInitView();

}