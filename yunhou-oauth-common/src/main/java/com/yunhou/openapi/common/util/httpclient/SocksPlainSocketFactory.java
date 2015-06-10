package com.yunhou.openapi.common.util.httpclient;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.params.HttpParams;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class SocksPlainSocketFactory extends PlainSocketFactory {
    public static final String SOCKS_PROXY_HOST = "socks.proxyHost";
    public static final String SOCKS_PROXY_PORT = "socks.proxyPort";

    @Override
    public Socket createSocket(final HttpParams params) {
        String proxyHost = (String) params.getParameter(SOCKS_PROXY_HOST);
        int proxyPort = (Integer) params.getParameter(SOCKS_PROXY_PORT);
        InetSocketAddress socksAddr = new InetSocketAddress(proxyHost, proxyPort);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksAddr);
        Socket socket = new Socket(proxy);
        return socket;
    }
}