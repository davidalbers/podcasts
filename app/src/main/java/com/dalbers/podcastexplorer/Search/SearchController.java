package com.dalbers.podcastexplorer.search;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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

import com.dalbers.podcastexplorer.contracts.SearchContract;
import com.dalbers.podcastexplorer.dagger.DaggerPodcastFinder;
import com.dalbers.podcastexplorer.dagger.PodcastFinder;
import com.dalbers.podcastexplorer.dagger.PresenterModule;
import com.dalbers.podcastexplorer.data.Podcast;

/**
 * Created by davidalbers on 1/7/17.
 */

public class SearchController extends Controller implements SearchContract.View {

    private RecyclerView podcastList;
    private PodcastListAdapter adapter;
    @Inject
    public SearchContract.Presenter presenter;
    private EditText searchBar;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //Only care about when text is done changing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Don't care about what specifically changed, we're using the whole string
        }

        @Override
        public void afterTextChanged(Editable s) {
            presenter.searchPodcasts(s.toString());
        }
    };

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if(keyEvent == null)
                return false;
            if (i == EditorInfo.IME_ACTION_SEARCH
                    || (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
                    keyEvent.getAction() == KeyEvent.ACTION_DOWN)) {
                if (getActivity() != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchBar.getWindowToken(), 0);
                }
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
    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_search, container, false);

        searchBar = (EditText) view.findViewById(R.id.edit_text_search_podcast);
        searchBar.setOnEditorActionListener(editorActionListener);
        searchBar.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchBar.addTextChangedListener(textWatcher);

        podcastList = (RecyclerView) view.findViewById(R.id.podcast_list);
        podcastList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        podcastList.setLayoutManager(layoutManager);
        adapter = new PodcastListAdapter(itemClickListener);
        podcastList.setAdapter(adapter);

        PodcastFinder podcastFinder = DaggerPodcastFinder
                .builder()
                .presenterModule(new PresenterModule(this))
                //com.dalbers.podcastexplorer.data has already been build by base app
                .dataComponent(((BaseApplication) getActivity().getApplication()).getDataComponent())
                .build();
        podcastFinder.inject(this);

        return view;
    }

    @Override
    public void onPodcastsLoaded(List<Podcast> podcasts) {
        adapter.setPodcasts(podcasts);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showPlaying() {
        //TODO: implement
    }

    @Override
    public void showPaused() {
        //TODO: implement
    }
}
