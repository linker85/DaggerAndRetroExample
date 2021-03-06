package com.example.com.examw4.module;

import com.example.com.examw4.model2.Example;
import com.example.com.examw4.recycler.NotificationsAdapter;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by raul on 09/11/2016.
 */
@Module
public class MyRetroModule {

    public static final String BASE_URL = "https://api.airbnb.com";

    private String key = "";

    public MyRetroModule (String key) {
        this.key = key;
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
    public Observable<Example> provideCreateLogin(Retrofit retrofit) {
        LoginService loginService = retrofit.create(LoginService.class);
        return loginService.getUsers(key);
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }


    public interface LoginService {
        @GET("/v2/search_results?client_id")
        Observable<Example> getUsers(@Query("key") String key);
    }
}