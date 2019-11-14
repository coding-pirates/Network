package de.upb.codingpirates.battleships.network.annotations.scope;

import com.google.inject.ScopeAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Paul Becker
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@ScopeAnnotation
public @interface ConnectionScoped {
}
