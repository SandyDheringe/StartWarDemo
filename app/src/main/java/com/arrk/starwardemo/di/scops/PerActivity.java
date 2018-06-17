package com.arrk.starwardemo.di.scops;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Sandeep.dheringe
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity
{
}
