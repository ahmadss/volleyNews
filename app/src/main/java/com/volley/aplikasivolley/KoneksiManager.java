package com.volley.aplikasivolley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by ahmad on 31/12/2015.
 */
public class KoneksiManager {

    private static RequestQueue SQueue;

    private static ImageLoader SiImageLoader;

    public static RequestQueue getInstance(Context context){
        if (SQueue == null){
            SQueue = Volley.newRequestQueue(context);
        }

        return SQueue;
    }

    public static ImageLoader getImageLoader(Context context){
        if (SiImageLoader == null){
            SiImageLoader = new ImageLoader(getInstance(context), new ImageLoader.ImageCache() {
                private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

                @Override
                public Bitmap getBitmap(String url) {
                    return mCache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    mCache.put(url, bitmap);
                }
            });
        }

        return SiImageLoader;
    }

//    RequestQueue queue = Volley.newRequestQueue(this);
}
