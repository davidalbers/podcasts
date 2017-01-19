package com.dalbers.podcastexplorer.data;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.dalbers.podcastexplorer.contracts.SearchContract;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by davidalbers on 1/8/17.
 */

public class GetPodcasts implements SearchContract.Model {

    private SearchContract.Presenter presenter;
    private static final String API_URL = "https://itunes.apple.com";
    private Subscription podcastSearchSubscription;
    private PublishSubject<String> podcastSearchSubject;

    public GetPodcasts() {
        setupObservers();
    }

    /**
     * Setup all the rx stuff so that a podcast will be searched for when podcastSearchSubject
     * gets a new string. The presenter will be notified when a success query returns
     */
    private void setupObservers() {
        //accepts a string onNext(), will put that string into the map function
        podcastSearchSubject = PublishSubject.create();

        podcastSearchSubscription = podcastSearchSubject
                //only run after n millis of inactivity
                .debounce(100, TimeUnit.MILLISECONDS)
                //observe the map on io thread (non-UI)
                .observeOn(Schedulers.io())
                //what to do with the strings input by our subject
                .map(new Func1<String, List<Podcast>>() {
                         @Override
                         public List<Podcast> call(String searchTerm) {
                             try {
                                 return doSearch(searchTerm);
                             } catch (Exception ex) {
                                 //rx will catch any exception,
                                 //we just have to rethrow it as a runtime exception
                                 throw Exceptions.propagate(ex);
                             }
                         }
                     })
                //observe the output on UI thread
                .observeOn(AndroidSchedulers.mainThread())
                //do stuff when our podcast search returns/fails
                .subscribe(podcastUpdateObserver);
    }

    @Override
    public void searchPodcasts(String searchTerm) {
        podcastSearchSubject.onNext(searchTerm);
    }

    Observer<List<Podcast>> podcastUpdateObserver = new Observer<List<Podcast>>() {
                    @Override
                    public void onCompleted() {
                        //only care about on next
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("GetPodcast","Error searching");
                    }

                    @Override
                    public void onNext(List<Podcast> podcasts) {
                        presenter.onPodcastsLoaded(podcasts);
                    }
                };

    /**
     * Do a generic search based on the term provided
     * @param searchTerm term to search for
     * @return a list of matching podcasts
     * @throws IOException
     */
    private List<Podcast> doSearch(String searchTerm) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PodcastSearchApi searchApi = retrofit.create(PodcastSearchApi.class);
        Call<PodcastResults> call = searchApi.search(searchTerm, "us");
        Log.d("GetPodcast",call.request().url().toString());
        return call.execute().body().podcasts;
    }


    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private interface PodcastSearchApi {
        @GET("search?media=podcast")
        Call<PodcastResults> search(@Query("term") String term,
                                   @Query("country") String countryCode);
    }

    private class PodcastResults {
        @SerializedName("results")
        List<Podcast> podcasts;
    }
}
