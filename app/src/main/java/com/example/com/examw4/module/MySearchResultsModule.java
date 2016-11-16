package com.example.com.examw4.module;

import com.example.com.examw4.model2.SearchResult;
import com.example.com.examw4.recycler.NotificationsAdapter;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by raul on 15/11/2016.
 */
@Module
public class MySearchResultsModule {
    public static final String BASE_URL = "https://api.airbnb.com";

    private String clientId = "";

    public MySearchResultsModule (String clientId) {
        this.clientId = clientId;
    }

    @Provides
    public NotificationsAdapter providesNotificationManager() {
        return new NotificationsAdapter();
    }

    @Provides
    public Retrofit provideRetrofitFactory() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    public Observable<List<SearchResult>> provideCreateLogin(Retrofit retrofit) {
        SearchResultService searchResultService = retrofit.create(SearchResultService.class);
        return searchResultService.getSearchResult(clientId);
    }


    public interface SearchResultService {
        @GET("/v2/search_results?client_id={clientId}")
        Observable<List<SearchResult>> getSearchResult(@Path("clientId") String key);
    }
}
