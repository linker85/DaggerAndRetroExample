package com.example.com.examw4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.com.examw4.component.DaggerMyComponent;
import com.example.com.examw4.model2.Example;
import com.example.com.examw4.model2.SearchResult;
import com.example.com.examw4.module.MyRetroModule;
import com.example.com.examw4.recycler.NotificationsAdapter;
import com.example.com.examw4.recycler.SimpleDecorator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";
    @Inject
    Observable<Example> retroModule;

    @BindView(R.id.a_notifications_recycler)
    public RecyclerView notificationRecyclerView;

    List<SearchResult> notificationArrayList;
    @Inject
    NotificationsAdapter notificationAdapter;

    MyRetroModule myRetro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        myRetro = new MyRetroModule("3092nxybyb0otqw18e8nh5nty");

        DaggerMyComponent.builder().
                myRetroModule(myRetro).
                build().
                inject(this); // instance
    }

    public void doMagic(View view) {
        retroModule
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Example>() {
                    @Override
                    public void onStart() {
                        Log.d(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }


                    @Override
                    public void onNext(Example example) {

                        List<SearchResult> list = example.getSearchResults();
                        // 1. get a reference to recyclerView

                        // 2. set layoutManger
                        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        // 3. Get data from database
                        notificationArrayList = example.getSearchResults();
                        // 4. set adapter
                        notificationAdapter.setNotificationsArrayList(notificationArrayList);
                        //notificationAdapter = new NotificationsAdapter(notificationArrayList);
                        notificationRecyclerView.setAdapter(notificationAdapter);
                        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        notificationRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        notificationRecyclerView.addItemDecoration(new SimpleDecorator(getApplicationContext(), LinearLayoutManager.VERTICAL));
                        // 5. notify changes
                        notificationAdapter.notifyDataSetChanged();

                    }
                });
    }

}