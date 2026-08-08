package com.shakthi.jobmanagement.job;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(){};

    public JobNotFoundException (String message){
        super(message);
    }

    public JobNotFoundException (Throwable cause){
        super(cause);
    }

    public JobNotFoundException(String message,Throwable cause){
        super(message, cause);
    }
}
