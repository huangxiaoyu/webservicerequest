package com.thinfreely.app.webservicerequest.tool;

import com.thinfreely.app.webservicerequest.network.Callback;
import com.thinfreely.app.webservicerequest.network.SoapClient;
import com.thinfreely.app.webservicerequest.network.SoapRequest;

import org.ksoap2.SoapEnvelope;

import java.util.HashMap;

/**
 * Created by thinkfreely on 2017/3/6.
 */

public class SoapUtil {
    private static final String TAG = "SoapUtil";
    private static SoapUtil mInstance;
    private SoapClient mSoapClient;

    public static final String mWeatherEndPoint = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

    public static final String mNameSpace = "http://WebXml.com.cn/";
    public int mSOAPVersion = SoapEnvelope.VER11;

    private SoapUtil() {
        mSoapClient = new SoapClient();
        //设置是否是调试模式
        mSoapClient.setDebug(true);
    }

    /**
     * 初始化方法
     *
     * @return
     */
    public static synchronized SoapUtil getInstance() {
        if (mInstance == null) {
            mInstance = new SoapUtil();
        }
        return mInstance;
    }


    /**
     * 同步调用 单个参数
     *
     * @param endPoint    webservice地址
     * @param nameSpace   webservice命名空间
     * @param methodName  方法名
     * @param paramKey    单个参数key
     * @param paramsValue 参数值
     * @param callback    回调
     */
    public void sendAsync(String endPoint, String nameSpace, String methodName, String paramKey, String paramsValue, Callback callback) {
        SoapRequest request = new SoapRequest.Builder().endPoint(endPoint)
                .methodName(methodName)
                .soapAction(nameSpace + methodName)
                .addParam(paramKey, paramsValue)
                .nameSpace(nameSpace)
                .setVersion(SoapEnvelope.VER11)
                .setDotNet(true)
                .build();
        mSoapClient.newCall(request).enqueue(callback);
    }

    /**
     * 异步调用 多个参数
     *
     * @param endPoint   webservice地址
     * @param nameSpace  webservice命名空间
     * @param methodName 方法名
     * @param params     参数键值对
     * @param callback   回调
     */
    public void sendAsync(String endPoint, String nameSpace, String methodName, HashMap<String, Object> params, Callback callback) {
        SoapRequest request = new SoapRequest.Builder().endPoint(endPoint)
                .methodName(methodName)
                .soapAction(nameSpace + methodName)
                .addParam(params)
                .nameSpace(nameSpace)
                .setVersion(SoapEnvelope.VER11)
                .setDotNet(true)
                .build();
        mSoapClient.newCall(request).enqueue(callback);
    }

    /**
     * 同步调用 单个参数
     *
     * @param endPoint
     * @param nameSpace
     * @param methodName
     * @param paramKey
     * @param paramsValue
     * @return
     */
    public SoapEnvelope send(String endPoint, String nameSpace, String methodName, String paramKey, String paramsValue) {
        SoapRequest request = new SoapRequest.Builder().endPoint(endPoint)
                .methodName(methodName)
                .soapAction(nameSpace + methodName)
                .addParam(paramKey, paramsValue)
                .nameSpace(nameSpace)
                .setVersion(SoapEnvelope.VER11)
                .setDotNet(true)
                .build();
        return mSoapClient.newCall(request).execute();
    }

    /**
     * 同步调用 多个参数
     *
     * @param endPoint   webservice地址
     * @param nameSpace  命名空间
     * @param methodName 方法名
     * @param params     参数
     * @return
     */
    public SoapEnvelope send(String endPoint, String nameSpace, String methodName, HashMap<String, Object> params) {
        SoapRequest request = new SoapRequest.Builder().endPoint(endPoint)
                .methodName(methodName)
                .soapAction(nameSpace + methodName)
                .addParam(params)
                .nameSpace(nameSpace)
                .setVersion(SoapEnvelope.VER11)
                .setDotNet(true)
                .build();
        return mSoapClient.newCall(request).execute();
    }

    /**
     * 同步调用
     *
     * @param cityName
     * @return
     */
    public SoapEnvelope getSupportCity(String cityName) {
        SoapRequest request = new SoapRequest.Builder().endPoint(mWeatherEndPoint)
                .methodName("getSupportCity")
                .soapAction(mNameSpace + "getSupportCity")
                .addParam("byProvinceName", cityName)
                .nameSpace(mNameSpace)
                .setVersion(mSOAPVersion)
                .setDotNet(true)
                .build();
        return mSoapClient.newCall(request).execute();
    }

    /**
     * 异步调用
     *
     * @param cityName
     * @param callback
     */
    public void getSupportCity(String cityName, Callback callback) {
        SoapRequest request = new SoapRequest.Builder().endPoint(mWeatherEndPoint)
                .methodName("getSupportCity")
                .soapAction(mNameSpace + "getSupportCity")
                .addParam("byProvinceName", cityName)
                .nameSpace(mNameSpace)
                .setVersion(mSOAPVersion)
                .setDotNet(true)
                .build();
        mSoapClient.newCall(request).enqueue(callback);
    }
}
