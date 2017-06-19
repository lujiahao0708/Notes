package com.android.volley.plus;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rodrigo
 * Date: 13-11-4
 * Time: 下午2:55
 */
public interface PersistentCache {
    /**
     * set a cache item to be persistent. Persistent cache items will not be removed when trimming, and their size is excluded in total size of disk cache.
     * Remember call setBrittle if an item needs no longer to be kept.
     * @param key
     */
    public void setPersistent(String key) throws IOException;

    /**
     * set a cache item to be brittle, no longer persistent.
     * @param key
     */
    public void setBrittle(String key) throws IOException;
}
