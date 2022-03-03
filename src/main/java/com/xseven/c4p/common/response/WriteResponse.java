package com.xseven.c4p.common.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName WriteResponse
 * @Author: xseven
 * @Description TODO
 * @Date 2022/3/3 15:41
 * @Version 1.0
 */
public class WriteResponse {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void write(HttpServletResponse httpServletResponse, Result restResponse) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = httpServletResponse.getWriter();
        out.write(mapper.writeValueAsString(restResponse));
        out.flush();
        out.close();
    }

}
