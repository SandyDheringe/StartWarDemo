package com.arrk.starwardemo.repository;

/**
 *@author Sandeep.dheringe
 */
public class NetworkState
{

    public static NetworkState LOADED = new NetworkState(Status.SUCCESS);
    public static NetworkState LOADING = new NetworkState(Status.RUNNING);
    @Status
    private int status;
    private String message;

    private NetworkState(@Status int status, String message)
    {
        this.status = status;
        this.message = message;
    }

    private NetworkState(int status)
    {
        this.status = status;
    }

    public static NetworkState error(String message)
    {
        return new NetworkState(Status.FAILED, message == null ? "unknown error" : message);
    }

    public int getStatus()
    {
        return status;
    }

    public String getMessage()
    {
        return message;
    }

}
