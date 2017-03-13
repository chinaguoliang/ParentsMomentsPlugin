package com.jgkj.plugin; /**
 * Created by chen on 17/3/13.
 */
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 增加对跨域访问的支持
 *
 */

@ControllerAdvice(basePackages = "com.jgkj.plugin.controllers")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice{
    public JsonpAdvice() {
        super("callback","jsonp");
    }
}