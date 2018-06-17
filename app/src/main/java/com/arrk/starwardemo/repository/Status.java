package com.arrk.starwardemo.repository;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Sandeep.dheringe
 */

@IntDef({Status.RUNNING, Status.SUCCESS, Status.FAILED})
@Retention(RetentionPolicy.SOURCE)
public @interface Status
{
    int RUNNING = 0;
    int SUCCESS = 1;
    int FAILED = 2;
}
