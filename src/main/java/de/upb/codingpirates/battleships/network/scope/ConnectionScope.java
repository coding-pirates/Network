package de.upb.codingpirates.battleships.network.scope;

import com.google.common.collect.Maps;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Scopes;
import de.upb.codingpirates.battleships.network.id.Id;

import java.util.Map;

/**
 * @author Paul Becker
 */
public class ConnectionScope implements Scope {

    private final ThreadLocal<Id> current = new ThreadLocal<>();
    private final ThreadLocal<Map<Id, Map<Key<?>, Object>>> objects = new ThreadLocal<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T> Provider<T> scope(Key<T> key, Provider<T> unscoped) {
        return () -> {
            Map<Key<?>, Object> scopedObjects = getScopedObjectsMap();

            if (scopedObjects == null)
                return unscoped.get();

            T current = (T) scopedObjects.get(key);
            if (current == null && !scopedObjects.containsKey(key)) {
                current = unscoped.get();
                if (Scopes.isCircularProxy(current)) {
                    return current;
                }
                scopedObjects.put(key, current);
            }
            return current;
        };
    }

    public void enter(Id connectionId) {
        if (this.current.get() == null) {
            current.set(connectionId);
        }
    }

    public void exit() {
        if (this.current.get() != null) {
            current.remove();
        }
    }

    @SuppressWarnings("WeakerAccess")
    public <T> void seed(Key<T> key, T value) {
        Map<Key<?>, Object> scopedObjects = getScopedObjectsMap();
        assert scopedObjects != null;
        scopedObjects.putIfAbsent(key, value);
    }

    public <T> void seed(Class<T> clazz, T value) {
        this.seed(Key.get(clazz), value);
    }

    private <T> Map<Key<?>, Object> getScopedObjectsMap() {
        Id currentKey = current.get();
        Map<Id, Map<Key<?>, Object>> objectsByKey = objects.get();
        if (objectsByKey == null) {
            objects.set(Maps.newHashMap());
            objectsByKey = objects.get();
        }

        if (currentKey != null && objectsByKey != null) {
            objectsByKey.putIfAbsent(currentKey, Maps.newHashMap());

            return objectsByKey.get(currentKey);
        }
        return null;
    }
}
