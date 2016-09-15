package io.netopen.hotbitmapgg.androideverydaypractice.imageloader;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.netopen.hotbitmapgg.androideverydaypractice.R;

/**
 * Created by gzoom on 2016/9/7.
 */
public class ImageLoader {
    private static final String TAG="ImageLoader";
    public static final int MESSAGE_POST_RESULT=1;
    private static final int CPU_COUNT=Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE=CPU_COUNT+1;
    private static final int MAXIMUM_POOL_SIZE=CPU_COUNT*2+1;
    private static final long KEEP_ALIVE=10L;

//    private static final int TAG_KEY_URI= R.id.imageloader_uri;
    private static final long DISK_CACHE_INDEX=0;
    private boolean mIsDiskLruCacheCreated=false;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount=new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable,"ImageLoader#"+mCount.getAndIncrement());
        }
    };
    private static final Executor THREAD_POOL_EXECUTOR=new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,
            KEEP_ALIVE, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(),sThreadFactory);
}
