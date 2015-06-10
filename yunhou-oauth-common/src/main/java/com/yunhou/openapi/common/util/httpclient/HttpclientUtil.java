package com.yunhou.openapi.common.util.httpclient;

import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.TruncatedChunkException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpclientUtil {
    private static final String EASY = "easy";

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    /**
     * 连接超时
     */
    private int connectionTimeout = 20000;

    /**
     * socks超时
     */
    private int socksTimeout = 20000;

    public static final String RESPONSE_BYTE_ARRY_TYPE = "byteArray";

    private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.52 Safari/537.17";

    private static final String GET = "GET";

    private static final String POST = "POST";

    private static final String DEFAULT_CHARSET = "utf-8";

    private static final int RETRY_COUNT = 1;

    private boolean keepConnection = false;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    protected DefaultHttpClient client;

    public HttpclientUtil() {
    }

    public DefaultHttpClient getClient() {
        return client;
    }

    public void setClient(DefaultHttpClient client) {
        this.client = client;
    }

    /**
     * 创建一个httpclient
     * 
     * @param maxCount
     * @throws Exception
     */
    public static HttpclientUtil create(int maxCount) {
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
        BasicHttpParams params = new BasicHttpParams();
        params.setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);
        params.setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 64 * 1024);
        if (maxCount > 0) {
            cm.setMaxTotal(maxCount);
            cm.setDefaultMaxPerRoute(maxCount);
        }
        HttpclientUtil util = new HttpclientUtil();
        util.client = new DefaultHttpClient(cm, params);
        util.client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
        util.client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        util.client.getParams().setParameter(ClientPNames.MAX_REDIRECTS, 1000);
        util.client.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, util.connectionTimeout);
        util.client.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT, util.socksTimeout);
        util.client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, USER_AGENT);
        util.client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(RETRY_COUNT, false));
        return util;
    }

    /**
     * 创建一个httpclient
     * 
     * @param maxCount
     * @throws Exception
     */
    public static HttpclientUtil create(int maxCount, int socksTimeout, int connectionTimeout) {
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
        BasicHttpParams params = new BasicHttpParams();
        params.setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);
        params.setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 64 * 1024);
        if (maxCount > 0) {
            cm.setMaxTotal(maxCount);
            cm.setDefaultMaxPerRoute(maxCount);
        }
        HttpclientUtil util = new HttpclientUtil();
        util.client = new DefaultHttpClient(cm, params);
        util.client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
        util.client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        util.client.getParams().setParameter(ClientPNames.MAX_REDIRECTS, 1000);
        util.client.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, connectionTimeout);
        util.client.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT, socksTimeout);
        util.client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, USER_AGENT);
        util.client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(RETRY_COUNT, false));
        return util;
    }

    /**
     * 创建一个可以访问https的httpclient
     * 
     * @param maxCount
     * @return
     * @throws Exception
     */
    public static HttpclientUtil createHttps(int maxCount) throws Exception {
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, null, null);
        SSLSocketFactory sf = new SSLSocketFactory(sslcontext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme https = new Scheme("https", 443, sf);
        Scheme http = new Scheme("http", 80, PlainSocketFactory.getSocketFactory());
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(https);
        registry.register(http);
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager(registry);
        BasicHttpParams params = new BasicHttpParams();
        params.setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        params.setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 64 * 1024);
        if (maxCount > 0) {
            cm.setMaxTotal(maxCount);
            cm.setDefaultMaxPerRoute(maxCount);
        }
        HttpclientUtil util = new HttpclientUtil();
        util.client = new DefaultHttpClient(cm, params);
        util.client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
        util.client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        util.client.getParams().setParameter(ClientPNames.MAX_REDIRECTS, 1000);
        util.client.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, util.connectionTimeout);
        util.client.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT, util.socksTimeout);
        util.client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, USER_AGENT);
        util.client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(RETRY_COUNT, false));
        return util;
    }

    /**
     * 创建一个带pool的client
     * 
     * @return
     */
    public void shutdown() {
        client.getConnectionManager().shutdown();
    }

    /**
     * 设置代理信息
     * 
     * @param ip 代理IP
     * @param port 代理端口
     * @param proxyType 代理类型
     */
    public void setProxy(String ip, int port, Proxy.Type proxyType) {
        if (proxyType == Proxy.Type.SOCKS) {
            client.getParams().setParameter(SocksPlainSocketFactory.SOCKS_PROXY_HOST, ip);
            client.getParams().setParameter(SocksPlainSocketFactory.SOCKS_PROXY_PORT, port);
            client.getConnectionManager().getSchemeRegistry().register(new Scheme("http", 80, new SocksPlainSocketFactory()));
        } else if (proxyType == Proxy.Type.HTTP) {
            setProxy(ip, port);
        }
    }

    /**
     * 设置cookie持久扩展
     * 
     * @param cookieStore
     */
    public void setCookieStore(CookieStore cookieStore) {
        client.setCookieStore(cookieStore);
    }

    /**
     * 设置代理(http代理)
     * 
     * @param proxyIP 代理IP
     * @param proxyPort 代理端口
     */
    public void setProxy(String proxyIP, int proxyPort) {
        HttpHost proxy = new HttpHost(proxyIP, proxyPort);
        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
    }

    public String doGet(String url, Map<String, String> headers, String charset) throws Exception {
        return (String) doHttp(url, headers, null, GET, charset, null);
    }

    public byte[] doGetBytes(String url, Map<String, String> headers, String charset) throws Exception {
        return (byte[]) doHttp(url, headers, null, GET, charset, RESPONSE_BYTE_ARRY_TYPE);
    }

    public String doPost(String url, Map<String, String> headers, HttpEntity entity, String charset) throws Exception {
        if (entity instanceof UrlEncodedFormEntity) {
            Field content = StringEntity.class.getDeclaredField("content");
            content.setAccessible(true);
            byte[] o = (byte[]) content.get(entity);
            logger.info("post 的请求参数 : " + new String(o, charset == null ? "utf-8" : charset));
        }
        return (String) doHttp(url, headers, entity, POST, charset, null);
    }

    public String doGet(String url, String charset) throws Exception {
        return (String) doHttp(url, null, null, GET, charset, null);
    }

    private Object doHttp(String url, Map<String, String> headers, HttpEntity entity, String type, String charset,
            String reponseType) throws Exception {
        url = url.replace("|", "%124");
        Object reEntity = null;
        do {
            try {
                logger.info("{}:{}", type, url);
                reEntity = httpRequest(url, headers, entity, type, StringUtils.isBlank(charset) ? DEFAULT_CHARSET : charset,
                        reponseType);
            } catch (NetworkRetryException e) {
                logger.info("HTTP访问失败  " + e.getMessage() + "");
                throw e;
            }
        } while (reEntity == null);
        return reEntity;
    }

    // 根据类型请求http资源
    private Object httpRequest(String url, Map<String, String> headers, HttpEntity entity, String type, String charset,
            String responseType) throws Exception {
        HttpEntity resEntity = null;
        HttpRequestBase request = null;
        try {
            if (type.equals(GET)) {
                request = new HttpGet(url);
            } else if (type.equals(POST)) {
                request = new HttpPost(url);
                if (entity != null) {
                    ((HttpPost) request).setEntity(entity);
                }
            } else {
                throw new RuntimeException("未知操作类型" + type);
            }
            request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
            request.addHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
            if (!keepConnection) {
                request.addHeader("Connection", "close");
            }
            if (headers != null && headers.size() > 0) {
                for (Entry<String, String> element : headers.entrySet()) {
                    request.addHeader(element.getKey(), element.getValue());
                }
            }
            HttpResponse response = client.execute(request);
            resEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                if (responseType == null) {
                    return readEntityString(resEntity, charset);
                }
                if (responseType.equals(RESPONSE_BYTE_ARRY_TYPE)) {
                    return readEntityByteArray(resEntity);
                }
            } else if (response.getStatusLine().getStatusCode() == 302 || response.getStatusLine().getStatusCode() == 301) {
                Header firstHeader = response.getFirstHeader("location");
                logger.error("302跳转地址" + firstHeader.getValue());
                EntityUtils.consume(resEntity);
                return httpRequest(firstHeader.getValue(), null, null, GET, charset, responseType);
            }
            String entiry = EntityUtils.toString(response.getEntity());
            logger.error("访问" + url + "出现异常 httpcode:{} entity :{}", response.getStatusLine().getStatusCode(), entiry);
            throw new HttpRequestException("访问" + url + "出现异常 ", response.getStatusLine().getStatusCode(), entiry);
        } catch (Exception e) {
            if (request != null) {
                request.abort();
            }
            if ((e instanceof SocketException) || (e instanceof SocketTimeoutException) || (e instanceof ConnectTimeoutException)
                    || (e instanceof EOFException) || (e instanceof TruncatedChunkException)) {
                throw new NetworkRetryException(e.getMessage(), e);
            }
            throw e;
        } finally {
            if (resEntity != null) {
                EntityUtils.consume(resEntity);
            }
        }
    }

    protected String readEntityString(HttpEntity entity, String charset) throws ParseException, IOException {
        String e = EntityUtils.toString(entity, charset);
        EntityUtils.consume(entity);
        return e;
    }

    protected byte[] readEntityByteArray(HttpEntity entity) throws ParseException, IOException {
        byte[] e = EntityUtils.toByteArray(entity);
        EntityUtils.consume(entity);
        return e;
    }

    /**
     * 未编码的url创建httpEntity
     * 
     * @param unencodeUrlParams 未编码参数
     * @param charset
     * @return
     */
    public static HttpEntity createEntityFromtUNEncode(String unencodeUrlParams, String charset) {
        return new StringEntity(urldecode(unencodeUrlParams, charset != null ? Charset.forName(charset) : UTF_8),
                ContentType.create(URLEncodedUtils.CONTENT_TYPE, charset));
    }

    /**
     * 已编码的url创建httpEntity
     * 
     * @param encodeUrlParams 已编码参数
     * @param charset
     * @return
     */
    public static HttpEntity createEntityFromEncoded(String encodeUrlParams, String charset) {
        return new StringEntity(encodeUrlParams, ContentType.create(URLEncodedUtils.CONTENT_TYPE, charset));
    }

    public void setKeepConnection(boolean keepConnection) {
        this.keepConnection = keepConnection;
    }

    /**
     * url编码
     * 
     * @param content 需要编码的字符串
     * @param charset 编码格式
     * @return
     */
    public static String urldecode(String content, Charset charset) {
        if (content == null) {
            return null;
        }
        ByteBuffer bb = ByteBuffer.allocate(content.length());
        CharBuffer cb = CharBuffer.wrap(content);
        while (cb.hasRemaining()) {
            char c = cb.get();
            if (c == '%' && cb.remaining() >= 2) {
                char uc = cb.get();
                char lc = cb.get();
                int u = Character.digit(uc, 16);
                int l = Character.digit(lc, 16);
                if (u != -1 && l != -1) {
                    bb.put((byte) ((u << 4) + l));
                } else {
                    bb.put((byte) '%');
                    bb.put((byte) uc);
                    bb.put((byte) lc);
                }
            } else {
                bb.put((byte) c);
            }
        }
        bb.flip();
        return charset.decode(bb).toString();
    }
}
