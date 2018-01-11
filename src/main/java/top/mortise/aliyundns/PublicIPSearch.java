package top.mortise.aliyundns;

import com.mortise.utils.netutils.HttpRequestUtil;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PublicIPSearch {

    @Value("${PublicIPSearch}")
    String url;

    public  String getIP() {
        String result = "";

        String response_body = HttpRequestUtil.doGet(url,"");//
        Pattern pattern = Pattern.compile("((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))");
        Matcher match = pattern.matcher(response_body);
        if (match.find( ))
        {
            result = match.group(0);
        }
        return result;

    }
}
