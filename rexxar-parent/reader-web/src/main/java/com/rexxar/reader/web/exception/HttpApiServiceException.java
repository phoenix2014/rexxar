package com.rexxar.reader.web.exception;

public class HttpApiServiceException extends RuntimeException {

    private static final long serialVersionUID = -7995149703674561834L;

    private int errno;

    private String errmsg;

    public int getErrno() {
        return this.errno;
    }

    public String getErrmsg() {
        return this.errmsg;
    }

    public HttpApiServiceException(int errno, String errmsg) {
        super(errmsg);
        this.errno = errno;
        this.errmsg = errmsg;
    }

    public HttpApiServiceException(int errno, String errmsg, Throwable cause) {
        super(errmsg,cause);
        this.errno = errno;
        this.errmsg = errmsg;
    }
}
