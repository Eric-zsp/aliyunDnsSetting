package cn.joyconn.aliyundns;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class testController {

    @RequestMapping({"test"})
    public String insert( HttpServletRequest request){

        return "1234";
    }
}
