package com.java.butterfly.common.util;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.java.butterfly.system.ctrl.UserInfoController;

// TODO: Auto-generated Javadoc
/**
 * TODO(HttpRequest工具类).
 *
 * @author xulu
 * @version  v 1.0
 * @ClassName: HttpRequestUtil
 * @date: 2017-5-4 15:24:36
 */
public class HttpRequestUtil {
    
    /** The logger. */
    private static Logger logger = Logger.getLogger(UserInfoController.class);
    
    /**
     * TODO(主要是为了调试打印一下).
     *
     * @author xulu
     * @param request the request
     * @title: requestParametersShowLog
     * @date: 2017-5-4 15:24:36
     */
    public static void requestParametersShowLog(HttpServletRequest request) {
        Map<?, ?> map = request.getParameterMap();
        Iterator<?> iterator = map.keySet().iterator();
        logger.info("************************************************");
        logger.info("********************获取参数列表********************");
        while (iterator.hasNext()) {
            String key = String.valueOf(iterator.next());
            logger.info(key + " " + request.getParameter(key));
        }
        logger.info("************************************************");
    }
}
