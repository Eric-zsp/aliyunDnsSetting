package cn.joyconn.aliyundns;

public class DomainModel {
    private String  aliyun_region_id;
    private String  aliyun_access_key_id;
    private String  aliyun_access_key_secret;
    private String  aliyun_domain;
    private String  ddns_domains;

    public String getAliyun_region_id() {
        return aliyun_region_id;
    }

    public void setAliyun_region_id(String aliyun_region_id) {
        this.aliyun_region_id = aliyun_region_id;
    }

    public String getAliyun_access_key_id() {
        return aliyun_access_key_id;
    }

    public void setAliyun_access_key_id(String aliyun_access_key_id) {
        this.aliyun_access_key_id = aliyun_access_key_id;
    }

    public String getAliyun_access_key_secret() {
        return aliyun_access_key_secret;
    }

    public void setAliyun_access_key_secret(String aliyun_access_key_secret) {
        this.aliyun_access_key_secret = aliyun_access_key_secret;
    }

    public String getAliyun_domain() {
        return aliyun_domain;
    }

    public void setAliyun_domain(String aliyun_domain) {
        this.aliyun_domain = aliyun_domain;
    }

    public String getDdns_domains() {
        return ddns_domains;
    }

    public void setDdns_domains(String ddns_domains) {
        this.ddns_domains = ddns_domains;
    }
}
