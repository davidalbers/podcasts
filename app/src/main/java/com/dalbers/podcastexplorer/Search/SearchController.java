package com.dalbers.podcastexplorer.Search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.dalbers.podcastexplorer.BaseApplication;
import com.dalbers.podcastexplorer.PodcastListAdapter;
import com.dalbers.podcastexplorer.R;

import java.util.List;

import javax.inject.Inject;

import Contracts.SearchContract;
import Dagger.DaggerPodcastFinder;
import Dagger.PodcastFinder;
import Dagger.PresenterModule;
import Data.Podcast;

/**
 * Created by davidalbers on 1/7/17.
 */

public class SearchController extends Controller implements SearchContract.View {

    private RecyclerView podcastList;
    private PodcastListAdapter adapter;
    @Inject
    public SearchContract.Presenter presenter;
    private EditText searchBar;

    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_search, container, false);

        searchBar = (EditText) view.findViewById(R.id.edit_text_search_podcast);
        searchBar.setOnEditorActionListener(editorActionListener);
        searchBar.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        podcastList = (RecyclerView) view.findViewById(R.id.podcast_list);
        podcastList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        podcastList.setLayoutManager(layoutManager);
        adapter = new PodcastListAdapter(itemClickListener);
        podcastList.setAdapter(adapter);

        PodcastFinder podcastFinder = DaggerPodcastFinder
                .builder()
                .presenterModule(new PresenterModule(this))
                //data has already been build by base app
                .dataComponent(((BaseApplication) getActivity().getApplication()).getDataComponent())
                .build();
        podcastFinder.inject(this);

        return view;
    }

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if(keyEvent == null)
                return false;
            if (i == EditorInfo.IME_ACTION_SEARCH
                    || (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
                            keyEvent.getAction() == KeyEvent.ACTION_DOWN)) {
                presenter.searchPodcasts(textView.getText().toString());
                return true;
            }
            return false;
        }
    };

    private PodcastListAdapter.PodcastItemListener itemClickListener =
            new PodcastListAdapter.PodcastItemListener() {
        @Override
        public void onClick(View view) {
            int position = podcastList.getChildAdapterPosition(view);
            Podcast selectedPodcast = adapter.getItemAt(position);
            getRouter().pushController(RouterTransaction.with(new SearchDetailsController())
                    .pushChangeHandler(new HorizontalChangeHandler())
                    .popChangeHandler(new HorizontalChangeHandler()));
        }
    };

    @Override
    public void onPodcastsLoaded(List<Podcast> podcasts) {
        adapter.setPodcasts(podcasts);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);
    }

    @Override
    public void showPlaying() {

    }

    @Override
    public void showPaused() {

    }
}
