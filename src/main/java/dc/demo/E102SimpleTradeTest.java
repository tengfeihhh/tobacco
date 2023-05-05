package dc.demo;



import dc.demo.E102Base;
import java.io.UnsupportedEncodingException;



public class E102SimpleTradeTest extends E102Base {
    public static void main(String[] args) throws UnsupportedEncodingException {
        E102Base base = new E102Base();
        String xmlData = "<?xml version=\"1.0\" encoding = \"GBK\"?><BEDC><Message><commHead><tranCode>0001</tranCode><cifMaster>60000054842</cifMaster><entSeqNo>202302310011152306</entSeqNo><tranDate>20230231</tranDate><tranTime>131005</tranTime><retCode>000</retCode><entUserId>100001</entUserId><password>1q2w3e4r</password></commHead><Body><account>9550885267406100161</account></Body></Message></BEDC>";
        base.getResponseTemplate(xmlData);
//        System.out.println(System.getProperty("java.class.path"));
//        // 构建接口的请求模板对象
//        E102SimpleTradeRequestTemplate requestTemplate = new E102SimpleTradeRequestTemplate();
//        // 设置每一次独特的请求流水
//        requestTemplate.getHeader().setSeqNo(LocalDateTime.now().toString());
////        config.setEntCstNo("60000054842");
//        // 设置请求报文体里header的客户号信息
//        requestTemplate.getHeader().setEntCstNo(config.getEntCstNo());
//        // 设置去前置交易标识
//        requestTemplate.setTransFlag(TransFlag.OFF_PRE);
//        // 客户注意要通过《广发银行银企直联接口说明文档-XXXX直通车.docx》文档规范封装银企直联业务报文。
//        String xmlData = "<?xml version=\"1.0\" encoding = \"GBK\"?><BEDC><Message><commHead><tranCode>0001</tranCode><cifMaster>60000054842</cifMaster><entSeqNo>202302310011152306</entSeqNo><tranDate>20230231</tranDate><tranTime>131005</tranTime><retCode>000</retCode><entUserId>100001</entUserId><password>1q2w3e4r</password></commHead><Body><account>9550885267406100161</account></Body></Message></BEDC>";
//        //把原始业务报文以gbk格式编码后再转为base64
//        String base64Xml = Base64.encode(xmlData.getBytes(Charset.forName("gbk")));
//        //设置业务报文
//        requestTemplate.setTransData(base64Xml);
//
//        Response response = RequestSender.send(config, requestTemplate);
//        if(response.isSuccessful()){
//            System.out.println("success retSeqNo: "+((Header)response.getHeader()).getRetSeqNo());
//            E102SimpleTradeResponseTemplate responseTemplate = (E102SimpleTradeResponseTemplate) response.getBody();
//            System.out.println("response body ba se64 decode: "+ new String(Base64.decode(responseTemplate.getTransDataResp()), Charset.forName("gbk")));
//        } else {
//            System.out.println("failed retSeqNo: "+((Header)response.getHeader()).getRetSeqNo());
//        }
    }

}
