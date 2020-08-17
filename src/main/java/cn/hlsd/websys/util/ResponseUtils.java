package cn.hlsd.websys.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 *  <p> 使用response输出JSON </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/10/11 17:27
 */
@Slf4j
public class ResponseUtils {

    /**
     * 使用response输出JSON
     *
     * @param response
     */
    public static void out(ServletResponse response, Object object) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println((object));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

}
