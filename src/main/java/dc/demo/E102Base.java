package dc.demo;


import com.cgb.deib.sdk.*;
import com.cgb.deib.sdk.e1deib.e102.E102SimpleTradeRequestTemplate;
import com.cgb.deib.sdk.e1deib.e102.E102SimpleTradeResponseTemplate;
import com.cgb.deib.sdk.tradecode.e102.simpleTrade.TransFlag;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author qinpanyong
 * @date 2021/6/2 15:43
 * @since 21.06
 */
public class E102Base {
    protected static Configuration config;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    static{
        config = new Configuration();
        // 设置银行公钥证书
//        config.getSecurityManager().setBankPublicKeyFilePath("/var/www/html/gf/z22nn1x3m6r4.cer");
        config.getSecurityManager().setBankPublicKeyFilePath("C:/cgb/certs/1.3/z22nn1x3m6r4.cer");
        // 设置个人私钥证书
//        config.getSecurityManager().setPrivateKeyFilePath("/var/www/html/gf/60000054842.pfx", "666");
        config.getSecurityManager().setPrivateKeyFilePath("C:/cgb/certs/1.3/60000054842.pfx", "666");
        // 开启加解密、加验签管理类
        config.getSecurityManager().setEnabled(true);
        // 设置appId（可通过双击cer公钥文件证书可以查到，也可以通过SDK的方法读取）
        config.setAppId("42x47lckv98f");
        config.getHttpProps().setMaxConnPerURL(5);
        config.getHttpProps().setMaxConnTotal(20);
        // 与广发银行接入系统交互的请求格式为JSON
        config.setContentType(ContentType.Xml);
        config.setVersion("1.0.0");
        // 请求银行的路由，具体的交易上下文后面程序会自己组装
        config.setBankReqURL("https://ebank-yd03.test.cgbchina.com.cn:49081/deib/1ADEIB/E102");
//        config.setBankReqURL("http://21.96.246.87:9080/deib/1ADEIB/E102");
        // 与广发银行接入系统编码为UTF8
        config.setEncoder(Charset.forName("utf8"));
        // 设置银行会分配的客户号信息
        config.setEntCstNo("60000054842");

//        String reqBody = "";
//        Request request = RequestFactory.createSaaSRequest(config, reqBody);



    }


    public static String readReqBody(String jsonFileName){
//        try {
//            byte[] content = Files.readAllBytes(Paths.get(AcctTrxDetailQueryTest.class.getResource("/" + jsonFileName).toURI()));
//            return new String(content, config.getEncoder());
//        }catch(IOException e) {
//            e.printStackTrace();
//        }catch(URISyntaxException e) {
//            e.printStackTrace();
//        }
        return "";
    }

    /**
     * 等待秒数
     *
     * @param timeout
     */
    public static void await(long timeout){
        try {
            countDownLatch.await(30, TimeUnit.DAYS.SECONDS);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Response getResponseTemplate(String xmlData){
        // 构建接口的请求模板对象
        E102SimpleTradeRequestTemplate requestTemplate = new E102SimpleTradeRequestTemplate();
        // 设置每一次独特的请求流水
        requestTemplate.getHeader().setSeqNo(LocalDateTime.now().toString());
//        config.setEntCstNo("60000054842");
        // 设置请求报文体里header的客户号信息
        requestTemplate.getHeader().setEntCstNo(config.getEntCstNo());
        // 设置去前置交易标识
        requestTemplate.setTransFlag(TransFlag.OFF_PRE);
        // 客户注意要通过《广发银行银企直联接口说明文档-XXXX直通车.docx》文档规范封装银企直联业务报文。
//        xmlData = "<?xml version=\"1.0\" encoding = \"GBK\"?><BEDC><Message><commHead><tranCode>0001</tranCode><cifMaster>1000147787</cifMaster><entSeqNo>202302310011152306</entSeqNo><tranDate>20230231</tranDate><tranTime>131005</tranTime><retCode>000</retCode><entUserId>100001</entUserId><password><![CDATA[1q2w3e4r]]></password></commHead><Body><account>9550885267406100161</account></Body></Message></BEDC>";
        //把原始业务报文以gbk格式编码后再转为base64
        String base64Xml = Base64.encode(xmlData.getBytes(Charset.forName("gbk")));
        //设置业务报文
        requestTemplate.setTransData(base64Xml);


        System.out.println("EntCstNo:"+config.getEntCstNo()+"--BankReqURL:"+config.getBankReqURL()+"--AppId"+config.getAppId()+
        		"--contentype:"+config.getContentType()+"--encode:"+config.getEncoder());
        System.out.println("requestTemplate:"+new String(Base64.decode(base64Xml)));
        Response response = RequestSender.send(config, requestTemplate);
        System.out.println("response:"+response);
        if(response != null && response.isSuccessful()){
            System.out.println("success retSeqNo: "+((Header)response.getHeader()).getRetSeqNo());
            E102SimpleTradeResponseTemplate responseTemplate = (E102SimpleTradeResponseTemplate) response.getBody();
            System.out.println("response body base64 decode: "+ new String(Base64.decode(responseTemplate.getTransDataResp()), Charset.forName("gbk")));
            return response;
        } else {
            System.out.println("failed retSeqNo: "+((Header)response.getHeader()).getRetSeqNo());
        }

        return null;
    }

}
