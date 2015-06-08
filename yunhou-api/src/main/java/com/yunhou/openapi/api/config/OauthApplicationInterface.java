package com.yunhou.openapi.api.config;

import com.rop.RopRequest;
import com.rop.annotation.HttpAction;
import com.rop.annotation.IgnoreSignType;
import com.rop.annotation.NeedAccessTokenType;
import com.rop.annotation.ServiceMethod;
import com.yunhou.openapi.request.config.OauthApplicationRequest;

/**
 * 
 * 应用授权管理<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月4日 上午9:51:21
 * @version 1.0
 * @since JDK 1.7
 */
public interface OauthApplicationInterface {
    @ServiceMethod(method = "oauth.app.get.id", version = "1.0", httpAction = HttpAction.GET, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object getById(OauthApplicationRequest app);

    @ServiceMethod(method = "oauth.app.get", version = "1.0", httpAction = HttpAction.GET, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object get(RopRequest request);

    @ServiceMethod(method = "oauth.app.add", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object add(OauthApplicationRequest app);

    @ServiceMethod(method = "oauth.app.update", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object update(OauthApplicationRequest app);

    @ServiceMethod(method = "oauth.app.delete", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object delete(OauthApplicationRequest app);
}
