package Data;


import android.os.Handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Contracts.Contract;

/**
 * Created by DavidAlbers on 11/21/2016.
 */

public class FakeGetPodcasts implements Contract.Model {
    private Contract.Presenter presenter;
    private ArrayList<Podcast> podcasts;
    public FakeGetPodcasts() {
        podcasts = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, i * -1);
            Date date = cal.getTime();
            podcasts.add(new Podcast("Name" + podcasts.size(), "Title" + podcasts.size(), "Lorem ipsum" + podcasts.size(), date));
        }
    }

    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadPodcasts() {
        Handler handler = new Handler();
        handler.postDelayed(waitToSendBackData, 100);
    }

    final Runnable waitToSendBackData = new Runnable() {
        public void run() {
            presenter.onPodcastsLoaded(podcasts);
        }
    };

}
