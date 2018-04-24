package com.thinfreely.app.webservicerequest;

import com.thinfreely.app.webservicerequest.network.Callback;
import com.thinfreely.app.webservicerequest.tool.SoapEnvelopeUtil;
import com.thinfreely.app.webservicerequest.tool.SoapUtil;

import org.junit.Test;
import org.ksoap2.SoapEnvelope;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSend() {
        //        SoapEnvelope soapEnvelope = SoapUtil.getInstance().send("http://WebXml.com.cn/WebServices/WeatherWebService.asmx", "http://WebXml.com.cn", "getSupportCity", "byProvinceName", "河南");
        SoapEnvelope soapEnvelope = SoapUtil.getInstance().send("http://www.WebXml.com.cn/WebServices/WeatherWebService.asmx", "http://WebXml.com.cn/", "getSupportCity", "byProvinceName", "河南");
        //        SoapEnvelope soapEnvelope = SoapUtil.getInstance().getSupportCity("河南");
        String response = SoapEnvelopeUtil.getTextFromResponse(soapEnvelope);
        System.out.println("respone:  " + response);
    }

    @Test
    public void testSendAsync() {
        SoapUtil.getInstance().sendAsync("http://www.WebXml.com.cn/WebServices/WeatherWebService.asmx", "http://WebXml.com.cn/", "getSupportCity", "byProvinceName", "河南", new Callback() {
//        SoapUtil.getInstance().getSupportCity( "河南", new Callback() {
            @Override
            public void onResponse(SoapEnvelope envelope) {
                String response = SoapEnvelopeUtil.getTextFromResponse(envelope);
                System.out.println("respone:  " + response);
            }

            @Override
            public void onFailure(Object o) {

            }
        });

    }
}