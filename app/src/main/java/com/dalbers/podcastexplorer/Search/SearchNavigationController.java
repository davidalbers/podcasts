package com.dalbers.podcastexplorer.search;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.dalbers.podcastexplorer.R;

/**
 * Created by davidalbers on 1/8/17.
 */

public class SearchNavigationController extends Controller {
    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_search_navigation, container, false);
        FrameLayout childContainer = (FrameLayout)view.findViewById(R.id.search_navigation_container);
        Router childRouter = getChildRouter(childContainer).setPopsLastView(false);
        if (!childRouter.hasRootController()) {
            childRouter.setRoot(RouterTransaction.with(new SearchController()));
        }
        return view;
    }
}
