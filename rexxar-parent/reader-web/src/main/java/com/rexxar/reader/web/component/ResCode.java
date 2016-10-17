package com.rexxar.reader.web.component;

public enum ResCode {

	UNKONW_ERROR(-1,"服务器繁忙，请稍后再试"),
	
	NOVEL_ALREADY_EXIST_ERROR(20500,"小说在书架中已存在"),
	
	NOVEL_NOT_EXIST_ERROR(20501,"小说不存在"),
	
	INVALID_REQUEST_PARAM_ERROR(20503,"无效的请求参数"),

	INVALID_FILE_ERROR(20504,"请上传线索文件！"),
	
	INVALID_ASSIGN_SHOP_ERROR(20505,"请选择分配门店"),
	
	INVALID_FOLLOW_MSG_NULL_ERROR(20506,"请填写跟进记录!"),
	
	;
	
	public int errno;
	public String errmsg;
	
	private ResCode(int errno,String errmsg){
		this.errno = errno;
		this.errmsg = errmsg;
	}
	
}
