package Utils;

import java.util.ArrayList;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.LRUMap;

public class Cache<K, V> {

    private long timeToLive;
    private final LRUMap<K, CacheObject> cacheMap;

    protected class CacheObject {
        long lastAccessed = System.currentTimeMillis();
        V value;

        CacheObject(V value) {
            this.value = value;
        }
    }

    public Cache(long timeToLive, final long timerInterval, int maxItems) {
        this.timeToLive = timeToLive * 1000;
        cacheMap = new LRUMap<>(maxItems);

        if (this.timeToLive > 0 && timerInterval > 0) {
            Thread t = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(timerInterval * 1000);
                    } catch (InterruptedException ex) {}
                    cleanup();
                }
            });
            t.setDaemon(true);
            t.start();
        }
    }

    public V getLast() {
        synchronized (cacheMap) {
            if (cacheMap.isEmpty())
                return null;
            return cacheMap.get(cacheMap.lastKey()).value;
        }
    }

    public boolean isCached(K key) {
        synchronized (cacheMap) {
            return cacheMap.get(key) != null;
        }
    }

    public void put(K key, V value) {
        synchronized (cacheMap) {
            cacheMap.put(key, new CacheObject(value));
        }
    }

    public V get(K key) {
        synchronized (cacheMap) {
            CacheObject cacheObject = cacheMap.get(key);

            if (cacheObject == null)
                return null;
            else {
                cacheObject.lastAccessed = System.currentTimeMillis();
                return cacheObject.value;
            }
        }
    }

    public V getOrDefault(K key, V value) {
        synchronized (cacheMap) {
            CacheObject cacheObject = cacheMap.getOrDefault(key, new CacheObject(value));
            cacheObject.lastAccessed = System.currentTimeMillis();
            return cacheObject.value;
        }
    }

    public void remove(K key) {
        synchronized (cacheMap) {
            cacheMap.remove(key);
        }
    }

    public int size() {
        synchronized (cacheMap) {
            return cacheMap.size();
        }
    }

    private void cleanup() {
        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey;

        synchronized (cacheMap) {
            MapIterator<K, CacheObject> itr = cacheMap.mapIterator();
            deleteKey = new ArrayList<>((cacheMap.size() / 2) + 1);
            K key;
            CacheObject c;

            while (itr.hasNext()) {
                key = itr.next();
                c = itr.getValue();

                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }

        for (K key : deleteKey) {
            synchronized (cacheMap) {
                cacheMap.remove(key);
            }
            Thread.yield();
        }
    }
}