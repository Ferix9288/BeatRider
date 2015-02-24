package com.kilobolt.framework;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class Pool<T> {
    public interface PoolObjectFactory<T> {
        public T createObject();
    }

	private static final boolean DEBUG = false;

	private static final String TAG = "Pool";

    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
    private final int maxSize;

    public Pool(PoolObjectFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList<T>(maxSize);
    }

    public T newObject() {
        T object = null;

        if (freeObjects.size() == 0) {
            object = factory.createObject();
        	if (DEBUG) Log.i (TAG, "Created New Object. Id ::" + object.toString());
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        	if (DEBUG) Log.i (TAG, "Reuse Object. Id ::" + object.toString());

        }
        return object;
    }

    public void free(T object) {
        if (freeObjects.size() < maxSize)
            freeObjects.add(object);
    }
}
