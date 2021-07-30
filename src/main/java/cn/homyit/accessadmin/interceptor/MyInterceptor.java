package cn.homyit.accessadmin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

/**
 * @author Ziqiang CAO
 * @email 1213409187@qq.com
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取URL
        String requestURI = request.getRequestURI();

        String substring = requestURI.substring(1);
        int index = substring.indexOf("/");
        if (index != -1) {
            substring = substring.substring(0, index);
        }
        HashSet<String> urls = (HashSet<String>) request.getSession().getAttribute("module");
        boolean result = urls.stream().anyMatch(substring::equals);

        if (!result) {
            response.sendRedirect("/");
        }

        return result;
    }
}
