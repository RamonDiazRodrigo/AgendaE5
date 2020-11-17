package com.ae5.sige.encryption;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class Interceptor implements HandlerInterceptor {
   @Override
   public boolean preHandle
      (HttpServletRequest request, HttpServletResponse response, Object handler) 
      throws Exception {

		   Enumeration<String> aux1 = request.getHeaders("authorization");
		   while(aux1.hasMoreElements()) {
			
			 if(!Token.header(aux1.nextElement())) {
				 response.getWriter().write("{ \"error_description\": \"Invalid Value\"}");
				 response.setContentType("application/json");
				 response.setCharacterEncoding("UTF-8");
				 response.setStatus(401);
				 return false;
			 }
			 String aux = request.getRequestURL().toString();
			 System.out.println(aux);
		   }

      return true;
   }
}
