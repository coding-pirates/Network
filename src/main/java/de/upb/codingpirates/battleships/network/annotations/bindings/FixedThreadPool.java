package de.upb.codingpirates.battleships.network.annotations.bindings;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for a Provider with a fixed ThreadPool
 *
 * @author Paul Becker
 */
@SuppressWarnings("unused")
@BindingAnnotation
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FixedThreadPool {
    int size();
}
