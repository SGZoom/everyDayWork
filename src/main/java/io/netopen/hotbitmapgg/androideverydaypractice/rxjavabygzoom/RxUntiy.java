package io.netopen.hotbitmapgg.androideverydaypractice.rxjavabygzoom;


import android.util.Log;

import io.netopen.hotbitmapgg.androideverydaypractice.utils.GZoomLog;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by gzoom on 2016/9/13.
 */
public class RxUntiy {
    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Lalala");
            subscriber.onCompleted();
        }
    });

    Subscriber<String>subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            GZoomLog.LogYellow("ls",s);
        }
    };

    Action1<String>onnextAction=new Action1<String>() {
        @Override
        public void call(String s) {
            GZoomLog.LogYellow("lsls",s);
        }
    };

    public void main()
    {
        observable.subscribe(subscriber);
        observable.subscribe(onnextAction);
        Observable.just("Hello,World").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
    }
}
