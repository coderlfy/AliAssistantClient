package com.cat.common;

import com.cat.model.AliRate;
import com.cat.model.ClawlerDisableTimes;
import com.cat.model.SoapResult;
import com.cat.model.WcfServerEntity;

public class AliCache {
	public static WcfServerEntity _WcfServer = null;
	
	public static AliRate _AliRate = null;
	
	public static SoapResult _SoapResult = null;
	
	public static ClawlerDisableTimes _ClawlerDisableTimes = null;

	public static void Init() {
		if (_WcfServer == null)
			_WcfServer = new WcfServerEntity();

		_WcfServer.setServerIp(Config._ServerIP);
		_WcfServer.setPort(Config._Port);
	}
}
