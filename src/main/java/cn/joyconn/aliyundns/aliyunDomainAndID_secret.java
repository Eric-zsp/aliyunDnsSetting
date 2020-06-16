package cn.joyconn.aliyundns;

import com.aliyuncs.DefaultAcsClient;
public class aliyunDomainAndID_secret {
    private DefaultAcsClient client;
    private String domain ;
    private String allDomain ;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAllDomain() {
        return allDomain;
    }

    public void setAllDomain(String allDomain) {
        this.allDomain = allDomain;
    }

    public DefaultAcsClient getClient() {
        return client;
    }

    public void setClient(DefaultAcsClient client) {
        this.client = client;
    }
}
