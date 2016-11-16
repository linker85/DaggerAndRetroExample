package com.example.com.examw4.helper;

import com.example.com.examw4.model2.Example;

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

public class MySearchHelper {
    public static final String BASE_URL = "https://api.airbnb.com";

    public static class Factory {
        public static Retrofit create() {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }

        public static Observable<Example> createGetResult(String clientId) {
            Retrofit retrofit = create();
            SearchResultService searchResultService = retrofit.create(SearchResultService.class);
            return searchResultService.getSearchResult(clientId);
        }
    }

    public interface SearchResultService {
        @GET("/v2/search_results?client_id={clientId}")
        Observable<Example> getSearchResult(@Path("clientId") String key);
    }
}
