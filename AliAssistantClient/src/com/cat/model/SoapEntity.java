package com.cat.model;

import java.util.ArrayList;
import java.util.List;

public class SoapEntity {
	private static String _UrlFormatString = "http://%s:%d/%s";
	private static String _SOAPActionFormatString = "%s%s/%s";
	private String _nameSpace;
    public String getNameSpace() {
        return _nameSpace;
    }
    public void setNameSpace(String nameSpace) {
        this._nameSpace = nameSpace;
    }

	private String _url;
    public String getURL() {
        return _url;
    }
    public void setURL(String url) {
        this._url = url;
    }
    
	private String _soapAction;
    public String getSoapAction() {
        return _soapAction;
    }
    public void setSoapAction(String soapAction) {
        this._soapAction = soapAction;
    }
    
	private String _methodName;
    public String getMethodName() {
        return _methodName;
    }
    public void setMethodName(String methodName) {
        this._methodName = methodName;
    }
    private List<KeyValue> _postParams;
    public List<KeyValue> getPostParams() {
    	return _postParams;
	}
    public void appendPostParams(String key, Object value) {
		if(_postParams == null)
			this._postParams = new ArrayList<KeyValue>();
		
		KeyValue obj = new KeyValue();
		obj.setKey(key);
		obj.setValue(value);
		this._postParams.add(obj);
	}
    private WcfServerEntity _wcfServer = null;
    private String _interfaceName = "";
    public SoapEntity(WcfServerEntity wcfServer, String interfaceName) {
    	_wcfServer = wcfServer;
    	_interfaceName = interfaceName;
	}
    
    public SoapEntity Get() {
		if(this._methodName == null 
				|| _interfaceName == "" 
				|| this._nameSpace == "")
			return null;
		
		this._soapAction = String.format(_SOAPActionFormatString, 
				this._nameSpace, this._interfaceName, this._methodName);
		
		this._url = String.format(_UrlFormatString, 
				_wcfServer.getServerIp(), _wcfServer.getPort(), this._interfaceName);
		
		
		return this;
	}
}
