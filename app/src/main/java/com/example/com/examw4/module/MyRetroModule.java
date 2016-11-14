package com.example.com.examw4.module;

import com.example.com.examw4.helper.MyServiceRetrofitHelper;
import com.example.com.examw4.model.User;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by raul on 09/11/2016.
 */
@Module
public class MyRetroModule {

    public static final String BASE_URL = "http://www.mocky.io";

    private String key = "";

    public MyRetroModule (String key) {
        this.key = key;
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
    public Observable<List<User>> provideCreateLogin(Retrofit retrofit) {
        MyServiceRetrofitHelper.LoginService loginService = retrofit.create(MyServiceRetrofitHelper.LoginService.class);
        return loginService.getUsers(key);
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }


    public interface LoginService {
        @GET("/v2/{key}")
        Observable<List<User>> getUsers(@Path("key") String key);
    }
}