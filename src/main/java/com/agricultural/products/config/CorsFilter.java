package com.agricultural.products.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {//定义一个全局的跨域过滤器
    /**跨域资源共享过滤器，允许来自不同源的客户端访问后端服务
      */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 强制转换请求和响应对象为HTTP类型，以便访问HTTP特定的方法或头信息
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 获取请求的源（Origin）
        String curOrigin = request.getHeader("Origin");
//        System.out.println("跨域过滤器被执行，当前访问来源者为：" + curOrigin);
        // 设置响应头，允许来自请求源的跨域请求
        //该字段是必须的。它的值要么是请求时Origin字段的值，要么是一个*，表示接收任意域名的请求。
        // 设置响应头，允许来自请求源的跨域请求
        // 注意：在生产环境中，可能需要对curOrigin进行校验，确保它来自受信任的源
        //response.setHeader("Access-Control-Allow-Origin", curOrigin); // 这里为了示例没有开启动态设置，实际中可能使用固定的值或校验后设置
        response.setHeader("Access-Control-Allow-Origin", curOrigin);
        //该字段是必须的，用来列出浏览器的CORS请求会用到哪些HTTP方法
        // 设置响应头，指定允许的HTTP方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        //预检间隔时间
        // 设置预检请求的缓存时间（秒为单位）
        response.setHeader("Access-Control-Max-Age", "3600");
        //该字段是一个逗号分隔的字符串，指定浏览器CORS请求会额外发送的头信息字段
        // 设置响应头，指定允许的请求头
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,token,customercoderoute,authorization,conntectionid,Cookie,request-ajax");

        //Access-Control-Allow-Credentials：该字段可选。它的值是一个布尔值，表示是否允许发送Cookie。
        // 默认情况下，Cookie不包括在CORS请求之中，设为True，
        // 即表示服务器明确许可，Cookie可以包含在请求中，一起发送给服务器。
        // 这个值也只能设为True，如果服务器不要浏览器发送Cookie，删除即可
        //　Access-Control-Allow-Credentials为True的时候，Access-Control-Allow-Origin一定不能设置为“*”，否则报错
        // 设置响应头，允许跨域请求时携带凭证（如Cookie）
        // 注意：如果设置为true，则Access-Control-Allow-Origin不能设置为"*"，必须是指定的源
        response.setHeader("Access-Control-Allow-Credentials","true");

        // 浏览器默认会发起异常 OPTIONS 的请求方式 这个时候我们通过过滤器直接拦截返回200后就可以解决跨越问题
        // 如果是预检请求（OPTIONS），则直接返回200状态码，不继续执行过滤器链
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // 继续执行过滤器链中的下一个过滤器或目标资源
        chain.doFilter(request, response);
    }

    /**
     * 过滤器初始化方法
     * 可以在这里进行一些初始化操作，比如加载配置文件等
     *
     * @param filterConfig 过滤器配置对象
     * @throws ServletException Servlet异常时抛出
     */
    @Override
    public void init(FilterConfig filterConfig) {
        // 在这个示例中，我们没有进行任何初始化操作
    }
    /**
     * 过滤器销毁方法
     * 可以在这里释放过滤器使用的资源
     */
    @Override
    public void destroy() {
        // 在这个示例中，我们没有进行任何销毁操作
    }
}
