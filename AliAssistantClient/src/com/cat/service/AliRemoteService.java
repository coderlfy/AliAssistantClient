package com.cat.service;

import org.json.*;

import com.cat.model.*;
import com.cat.common.*;

public class AliRemoteService {
	private static String _interfaceNameString = "IAli";

	private SoapResult getSoapResult(String methodName) {
		SoapEntity soapEntity = new SoapEntity(AliCache._WcfServer,
				_interfaceNameString);
		soapEntity.setNameSpace(Config._defautNameSpace);
		soapEntity.setMethodName(methodName);
		soapEntity = soapEntity.Get();

		return UtliSoap.CallWebService(soapEntity);
	}

	public AliRate GetRate() {
		AliRate rate = new AliRate();
		
		SoapResult result = this.getSoapResult("GetJsonCurrentRate");
		if (result.getIsSuccess()) {
			try {
				String jsonString = result.getValue().toString();
				JSONTokener jsonParser = new JSONTokener(jsonString);
				JSONObject rateobj = (JSONObject) jsonParser.nextValue();
				rate.setFetchTime(rateobj.getString(AliRate.KeyNameFetchTime));
				rate.setMonth1To6(rateobj.getString(AliRate.KeyNameMonth1To6));
				rate.setMonth6To12(rateobj.getString(AliRate.KeyNameMonth6To12));
				rate.setMonth12To24(rateobj
						.getString(AliRate.KeyNameMonth12To24));
				rate.setIsSuccess(true);
			} catch (JSONException ex) {
				rate.setIsSuccess(false);
				rate.setErrorMsg(ex.toString());
			}
		} else {
			rate.setIsSuccess(false);
			rate.setErrorMsg(result.getErrorMsg());
		}
		return rate;
	}

	public ClawlerDisableTimes GetClawlerDisableTimes() {
		ClawlerDisableTimes disableTimes = new ClawlerDisableTimes();
		SoapResult result = this.getSoapResult("GetJsonClawlerDisableTime");
		if (result.getIsSuccess()) {
			try {
				String jsonString = result.getValue().toString();
				JSONTokener jsonParser = new JSONTokener(jsonString);
				JSONObject rateobj = (JSONObject) jsonParser.nextValue();
				disableTimes.setDisableTime1(rateobj.getString(ClawlerDisableTimes._KeyNameCrawlerDisableRuntimeMode1));
				disableTimes.setDisableTime2(rateobj.getString(ClawlerDisableTimes._KeyNameCrawlerDisableRuntimeMode2));
				disableTimes.setIsSuccess(true);
			} catch (JSONException ex) {
				disableTimes.setIsSuccess(false);
				disableTimes.setErrorMsg(ex.toString());
			}
		} else {
			disableTimes.setIsSuccess(false);
			disableTimes.setErrorMsg(result.getErrorMsg());
		}
		return disableTimes;
	}
	public SoapResult SaveClawlerDisableTimes(
			String disableTime1, String disableTime2) {
		SoapEntity soapEntity = new SoapEntity(AliCache._WcfServer,
				_interfaceNameString);
		soapEntity.setNameSpace(Config._defautNameSpace);
		soapEntity.setMethodName("SetClawlerDisableTime");
		soapEntity.appendPostParams("timeMode1Str", disableTime1);
		soapEntity.appendPostParams("timeMode2Str", disableTime2);
		soapEntity = soapEntity.Get();

		SoapResult result = UtliSoap.CallWebService(soapEntity);;
		if (result.getIsSuccess()) {
			try {
				String jsonString = result.getValue().toString();
				JSONTokener jsonParser = new JSONTokener(jsonString);
				JSONObject rateobj = (JSONObject) jsonParser.nextValue();
				result.setIsSuccess(rateobj.getString("success").equals("true"));
			} catch (JSONException ex) {
				result.setIsSuccess(false);
				result.setErrorMsg(ex.toString());
			}
		}
		return result;
	}
}
