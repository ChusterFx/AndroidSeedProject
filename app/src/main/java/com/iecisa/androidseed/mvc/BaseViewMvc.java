package com.iecisa.androidseed.mvc;

import android.content.Context;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import android.view.View;

import com.iecisa.androidseed.mvc.viewmvc.ObservableViewMvc;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * This is the base class which provides basic common functionality to MVC views implementations
 */
public abstract class BaseViewMvc<ListenerType> extends BaseObservable<ListenerType>
        implements ObservableViewMvc<ListenerType> {

    private View mRootView;
    private Unbinder mUnbinder;

    // ---------------------------------------------------------------------------------------------
    // region root View

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void bindView(Object target) {
        Unbinder unbinder = ButterKnife.bind(target, getRootView());
        setUnbinder(unbinder);
    }

    @Override
    public void unbindView() {
        if (getUnbinder() != null) {
            getUnbinder().unbind();
        }
    }

    /**
     * Set the root android view of this MVC view
     */
    protected void setRootView(View rootView) {
        mRootView = rootView;
    }

    public Unbinder getUnbinder() {
        return mUnbinder;
    }

    public void setUnbinder(Unbinder unbinder) {
        this.mUnbinder = unbinder;
    }

    // endregion root View
    // ---------------------------------------------------------------------------------------------

    /**
     * Convenience method for obtaining references to {@link View}s contained in the hierarchy of
     * the root {@link View}.<br>
     * This method uses Java's type inference in order to automatically cast the returned
     * {@link View}s to the type of the containing variable.
     * @return {@link View} object casted to the type inferred by Java's automatic type inference
     *         mechanism, or null
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(@IdRes int id) {
        return (T) mRootView.findViewById(id);
    }

    /**
     * Convenience method for obtaining reference to {@link Context}
     * @return instance of {@link Context} associated with the root {@link View} of this MVC view
     */
    protected Context getContext() {
        return getRootView().getContext();
    }

    /**
     * Convenience method for obtaining a string resource
     */
    protected String getString(@StringRes int id) {
        return getContext().getString(id);
    }

    /**
     * Convenience method for obtaining a string resource with format arguments.
     */
    protected String getString(@StringRes int id, Object... formatArgs) {
        return getContext().getString(id, formatArgs);
    }

}
