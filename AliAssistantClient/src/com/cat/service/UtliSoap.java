package com.cat.service;

import java.util.*;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



import com.cat.model.*;

public class UtliSoap {

	private static void addPropertys(SoapObject rpc,
			List<KeyValue> postParams) {

		for(int i=0;i<postParams.size();i++)
		{
			KeyValue tmp = postParams.get(i);
			rpc.addProperty(tmp.getKey(), tmp.getValue());
			
		}
		/*
		Iterator<Entry<String, Object>> iter = postParams.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<String, Object> currentEntry = (Map.Entry<String, Object>) iter
					.next();
			String key = currentEntry.getKey();
			Object value = currentEntry.getValue();
			System.out.println(key + " " + value);  
			rpc.addProperty(key, value);
		}
		*/
	}

	public static SoapResult CallWebService(SoapEntity soapEntity) {

		SoapResult result = new SoapResult();
		try {
			SoapObject rpc = new SoapObject(soapEntity.getNameSpace(),
					soapEntity.getMethodName());
			
			List<KeyValue> postparams = soapEntity.getPostParams();
			if(postparams != null)
				addPropertys(rpc, postparams);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);

			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);

			HttpTransportSE transport = new HttpTransportSE(soapEntity.getURL());
			// 调用WebService
			transport.call(soapEntity.getSoapAction(), envelope);

			SoapObject returnvalue = (SoapObject) envelope.bodyIn;
			if (null != returnvalue) {
				result.setIsSuccess(true);
				result.setValue(returnvalue.getProperty(0));
			}
			else {
				result.setIsSuccess(false);
				result.setErrorMsg(String.format("%s方法调用没有任何返回值！", soapEntity.getMethodName()));
			}
		} catch (Exception e) {
			result.setIsSuccess(false);
			result.setErrorMsg(e.toString());
			e.printStackTrace();
		}
		return result;
	}

}
