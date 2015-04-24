package com.cat.model;

public class WcfServerEntity {
	private String _serverIp;
    public String getServerIp() {
        return _serverIp;
    }
    public void setServerIp(String serverIp) {
        this._serverIp = serverIp;
    }
	private int _port;
    public int getPort() {
        return _port;
    }
    public void setPort(int port) {
        this._port = port;
    }

}
