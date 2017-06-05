package com.android.volley.plus;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * A transition listener, when the image is downloaded, show a transition animation, instead of show it directly. To improve user experience.
 * An object pool used to sustain memory use. If the listener will not be used anymore, recycle() should be called.
 * Created with IntelliJ IDEA.
 * Author: Rodrigo
 * Date: 13-11-1
 * Time: 2:29 pm
 */
public class TransitionImageListener implements ImageLoader.ImageListener {
    private ImageView view;
    private int defaultImageResId;
    private int errorImageResId;
    private static final int FADE_IN_TIME = 200;

    /*package*/ TransitionImageListener next;

    private static final Object sPoolSync = new Object();
    private static TransitionImageListener sPool;
    private static int sPoolSize = 0;

    private static final int MAX_POOL_SIZE = 50;

    public static TransitionImageListener obtain(ImageView view,
                                                 int defaultImageResId, int errorImageResId) {
        synchronized (sPoolSync) {
            if (sPool != null) {
                TransitionImageListener listener = sPool;
                sPool = listener.next;
                listener.next = null;
                sPoolSize--;
                listener.view = view;
                listener.defaultImageResId = defaultImageResId;
                listener.errorImageResId = errorImageResId;
                return listener;
            }
        }
        return new TransitionImageListener(view, defaultImageResId, errorImageResId);
    }

    /**
     * Return a Message instance to the global pool.  You MUST NOT touch
     * the Message after calling this function -- it has effectively been
     * freed.
     */
    public void recycle() {
        clearForRecycle();
        synchronized (sPoolSync) {
            if (sPoolSize < MAX_POOL_SIZE) {
                next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }

    /*package*/ void clearForRecycle() {
        defaultImageResId = 0;
        errorImageResId = 0;
        view = null;
    }

    private TransitionImageListener(ImageView view,
                                    int defaultImageResId, int errorImageResId) {
        this.view = view;
        this.defaultImageResId = defaultImageResId;
        this.errorImageResId = errorImageResId;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (errorImageResId != 0) {
            view.setImageResource(errorImageResId);
        }
    }

    @Override
    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
        if (response.getBitmap() != null) {
            if (isImmediate) {
                showNow(response.getBitmap());
            } else {
                transitBitmap(response.getBitmap());
            }
            recycle();
        } else if (defaultImageResId != 0 && defaultImageResId != -1) {
            view.setImageResource(defaultImageResId);
        }
    }

    private void showNow(Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    private void transitBitmap(Bitmap bitmap) {
        // Transition drawable with a transparent drwabale and the final bitmap
        final TransitionDrawable td =
                new TransitionDrawable(new Drawable[]{
                        new ColorDrawable(android.R.color.transparent),
                        new BitmapDrawable(view.getResources(), bitmap)
                });
        // Set background to loading bitmap
        if (defaultImageResId != 0 && defaultImageResId != -1) {
            view.setImageResource(defaultImageResId);
        } else {
            view.setImageDrawable(new ColorDrawable(0xffffffff));//没有的话用白色作为默认图
        }
        view.setImageDrawable(td);
        td.startTransition(FADE_IN_TIME);
    }
}
