package com.czklps.filter;

import com.czklps.utils.DBUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        try {
//            filterChain.doFilter(servletRequest,servletResponse);
//            DBUtils.commitAndClose();// 提交事务
//        } catch (Exception e) {
//            DBUtils.rollbackAndClose();//回滚事务
//            e.printStackTrace();
//            throw new RuntimeException(e);//把异常抛给Tomcat管理展示友好的错误页面
//        }
    }

    @Override
    public void destroy() {

    }
}
