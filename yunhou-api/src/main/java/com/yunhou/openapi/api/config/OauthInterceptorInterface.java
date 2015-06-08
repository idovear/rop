package com.yunhou.openapi.api.config;

import com.rop.RopRequest;
import com.rop.annotation.HttpAction;
import com.rop.annotation.IgnoreSignType;
import com.rop.annotation.NeedAccessTokenType;
import com.rop.annotation.ServiceMethod;
import com.yunhou.openapi.request.config.OauthInterceptorRequest;

/**
 * 
 * 拦截<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月4日 下午4:07:51
 * @version 1.0
 * @since JDK 1.7
 */
public interface OauthInterceptorInterface {
    @ServiceMethod(method = "oauth.interceptor.get.id", version = "1.0", httpAction = HttpAction.GET, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object getById(OauthInterceptorRequest app);

    @ServiceMethod(method = "oauth.interceptor.get", version = "1.0", httpAction = HttpAction.GET, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object get(RopRequest request);

    @ServiceMethod(method = "oauth.interceptor.add", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object add(OauthInterceptorRequest app);

    @ServiceMethod(method = "oauth.interceptor.update", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object update(OauthInterceptorRequest app);

    @ServiceMethod(method = "oauth.interceptor.delete", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object delete(OauthInterceptorRequest app);
}
