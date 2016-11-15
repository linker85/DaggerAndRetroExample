package com.example.com.examw4.component;

import com.example.com.examw4.MainActivity;
import com.example.com.examw4.module.MyRetroModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by raul on 09/11/2016.
 */
@Singleton
@Component(modules = {MyRetroModule.class}) // can receive modules or dependencies
public interface MyComponent {
    // Where to inject
    void inject(MainActivity mainActivity);
}