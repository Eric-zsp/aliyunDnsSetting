package top.mortise.aliyundns;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.mortise.utils.comm.FileHelper;
import top.mortise.utils.loghelper.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class dnshandle  implements CommandLineRunner {
    @Autowired
    PublicIPSearch publicIPSearch;
//    @Value("domaincfg.path")
//    String domaincfg_path;
    @Override
    public void run(String... strings) throws Exception {
        anylsisDomainSet();
        int i=0;
        while (true){

            LogHelper.logger().error(++i+"");
            eachDomain();
            Thread.sleep(1000*10);
        }
        
    }
    ObjectMapper mapper =new ObjectMapper();
    static List<aliyunDomainAndID_secret> domain_list = new ArrayList<>();
    void anylsisDomainSet() {


        int index=1;
            try
            {
                File file = ResourceUtils.getFile("classpath:config/domaincfg.json");
                if(file==null){
                    LogHelper.logger().error("没有找到domain配置文件路径");
                }
                LogHelper.logger().info("domain配置文件路径："+file.getAbsolutePath());
                String domainjson  ="";
                List<String> ss = FileHelper.readFileByLines(file.getAbsolutePath());
                if (ss.size() > 0) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    StringBuffer sb =  new StringBuffer();
                    for(String s : ss){
                        if(!s.trim().startsWith("//")){
                            sb.append(s.trim());
                        }
                    }
                    domainjson=sb.toString();
                } else {
                    LogHelper.logger().error("未能读domain配置文件");
                }


                DomainModel[] domainModels = mapper.readValue(domainjson,DomainModel[].class);
                for(DomainModel domainModel:domainModels){
                    aliyunDomainAndID_secret ads = new aliyunDomainAndID_secret();
                    IClientProfile profile = DefaultProfile.getProfile(domainModel.getAliyun_region_id(),
                            domainModel.getAliyun_access_key_id(),
                            domainModel.getAliyun_access_key_secret());
                    ads.setClient(new DefaultAcsClient(profile));
                    ads.setDomain(domainModel.getAliyun_domain());
                    ads.setAllDomain(domainModel.getDdns_domains());
                    domain_list.add(ads);
                }
                LogHelper.logger().info("domain配置文件加载完成："+ mapper.writeValueAsString(domain_list));
            }
            catch (Exception ex) {
                LogHelper.logger().error("读取domain配置文件异常:"+ex.getMessage());
            }

    }


     void eachDomain() {
        try
        {
            String ip = publicIPSearch.getIP();
            for (aliyunDomainAndID_secret ads : domain_list)
            {
                DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
                request.setDomainName(ads.getDomain());
                request.setTypeKeyWord( "A");
                // 发起请求，并得到 Response
                try
                {
                    DescribeDomainRecordsResponse response = ads.getClient().getAcsResponse(request);
                    for (DescribeDomainRecordsResponse.Record r : response.getDomainRecords())
                    {
                        String _the_value = r.getDomainName();
                        if (ads.getAllDomain().indexOf(";" + r.getRR() + ";") > -1)
                        {
                            if (!r.getValue().equals(ip))
                            {
                                UpdateDomainRecordRequest upRequest = new UpdateDomainRecordRequest();
                                upRequest.setRecordId(  r.getRecordId());
                                upRequest.setRR( r.getRR());
                                upRequest.setType(  r.getType());
                                upRequest.setValue( ip);
                                UpdateDomainRecordResponse upResponse = ads.getClient().getAcsResponse(upRequest);
                                LogHelper.logger().info("设置成功:"+ r.getRR()+"."+ads.getDomain()+";ip:"+ip);
                               // System.Console.WriteLine(upResponse.getRecordId());
                            }
                        }
                    }
                    //System.Console.WriteLine(response.TotalCount);
                }
                catch (ServerException ex)
                {
                    LogHelper.logger().error("ServerException:"+ex.getMessage());
                }
                catch (ClientException ex)
                {
                    LogHelper.logger().error("ClientException:"+ex.getMessage());
                }
            }
        }
        catch (Exception ex) {

            LogHelper.logger().error("eachDomain:"+ex.getMessage());
        }

    }

}
