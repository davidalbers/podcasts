package Data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import Contracts.SearchContract;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by davidalbers on 1/8/17.
 */

public class GetPodcasts implements SearchContract.Model {

    private SearchContract.Presenter presenter;
    private List<Podcast> podcasts;
    private static final String API_URL = "https://itunes.apple.com";

    @Override
    public void searchPodcasts(String searchTerm) {
        Single<List<Podcast>> podcastSearchSingle = Single.fromCallable(
                new PodcastSearchCallable(searchTerm));

        Subscription podcastSearchSubscription = podcastSearchSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<Podcast>>() {

                    @Override
                    public void onSuccess(List<Podcast> podcasts) {
                        presenter.onPodcastsLoaded(podcasts);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("GetPodcast","Error searching");
                    }
                });
    }


    private class PodcastSearchCallable implements Callable<List<Podcast>> {
        private String searchString;

        PodcastSearchCallable(@NonNull String searchString) {
            this.searchString = searchString;
        }

        @Override
        public List<Podcast> call() throws Exception {
            return doSearch(searchString);
        }
    }


    private List<Podcast> doSearch(String searchTerm) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PodcastSearchApi searchApi = retrofit.create(PodcastSearchApi.class);
        Call<PodcastResults> call = searchApi.search(searchTerm, "us");
        Log.d("GetPodcast",call.request().url().toString());
        List<Podcast> searchedPodcasts = null;
        searchedPodcasts = call.execute().body().podcasts;
        return searchedPodcasts;
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
