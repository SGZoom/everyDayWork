package io.netopen.hotbitmapgg.androideverydaypractice.rxjavabygzoom;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by gzoom on 2016/9/13.
 */
public class RxTest {
    public static void main(String[] args)
    {
        Observable.just("Hello world")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s+"be call";
                    }
                }).subscribe(s -> System.out.print(s));
    }
}
