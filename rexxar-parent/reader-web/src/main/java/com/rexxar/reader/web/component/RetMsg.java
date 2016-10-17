package com.rexxar.reader.web.component;

public class RetMsg {
	private Integer errno = 0;
	private String  errmsg;
	private  Object data;
	
	private static final RetMsg DEFAULT_RetMsg = RetMsg.newInstance(0, "ok");
	
	public RetMsg(Integer errno,String errmsg){
		this.errno = errno;
		this.errmsg  = errmsg;
	}
	
	public RetMsg(Integer errno,String errmsg,Object data){
		this.errno = errno;
		this.errmsg  = errmsg;
		this.data = data;
	}
	
	public RetMsg(Object data){
		this.errno = 0;
		this.errmsg  = "ok";
		this.data = data;
	}
	
	public Integer getErrno() {
		return errno;
	}
	
	public void setErrno(Integer errno) {
		this.errno = errno;
	}
	
	public String getErrmsg() {
		return errmsg;
	}
	
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	public static RetMsg newInstance(Integer errno,String errmsg){
		return new RetMsg(errno, errmsg);
	}
	
	public static RetMsg getDefault(){
		return DEFAULT_RetMsg;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

	public static RetMsg failed(int errno,String errmsg){
		return new RetMsg(errno,errmsg);
	}

	
	public static RetMsg failed(String errmsg){
		return new RetMsg(-1,errmsg);
	}

	public static RetMsg success(Object data){
		return new RetMsg(0,"ok",data);
	}
	

}
