package com.nexon.narket.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CommonInterceptor implements HandlerInterceptor {
	
	// private final Logger log = (Logger) LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
		HttpSession session = req.getSession();
		session.setAttribute("empNo", "24687");
		session.setAttribute("empNm", "윤호세");
		
		return true;
	}

}
