package com.dalbers.podcastexplorer.Search;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.dalbers.podcastexplorer.R;

/**
 * Created by davidalbers on 1/8/17.
 */

public class SearchDetailsController extends Controller {
    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_search_details, container, false);
        return view;
    }
}
