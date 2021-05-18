package com.example.rxjava

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val compositeDisposable = CompositeDisposable()

        /**Flowable handles BackPressure; such that it takes care of the number of emitters per second*/
        val flowable = Flowable.just("This is a Flowable")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> println("Received: $value") },
                { error -> println("Error: $error") },
                { println("Completed") }
            )

        /**Disposable is used to free up memory in the app and it is mostly used when an activity is destroyed */
            compositeDisposable.add(flowable)

    }
}