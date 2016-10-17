package com.rexxar.reader.web.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.rexxar.reader.web.exception.HttpApiServiceException;


public class Rsps {

	private static Logger log = LoggerFactory.getLogger(Rsps.class);

	private static final String RES_DATA = "data";

	public static RetMsg onResult(Loader loader) {
		RetMsg apiResponse = null;
		try {
			apiResponse = RetMsg.success(loader.load());
		} catch (HttpApiServiceException ex) {
			apiResponse = RetMsg.failed(ex.getErrno(), ex.getErrmsg());
		}catch (Exception ex) {
			log.error("catch error with parameters: ", ex);
			apiResponse = RetMsg.failed(ResCode.UNKONW_ERROR.errno,ResCode.UNKONW_ERROR.errmsg);
		}
		return apiResponse;
	}

	public static ModelAndView onView(Loader loader, String view) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName(view);
			mv.addObject(RES_DATA, loader.load());
		} catch (Exception ex) {
			log.error(" error with view={}  ex={}",view,ex);
		}
		return mv;
	}

	public static interface Loader {
		public Object load() throws Exception;
	}
}
