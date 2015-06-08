package com.yunhou.openapi.api.config;

import com.rop.RopRequest;
import com.rop.annotation.HttpAction;
import com.rop.annotation.IgnoreSignType;
import com.rop.annotation.NeedAccessTokenType;
import com.rop.annotation.ServiceMethod;
import com.yunhou.openapi.request.config.OauthLimitRequest;

public interface OauthLimitInterface {
    @ServiceMethod(method = "oauth.limit.get.id", version = "1.0", httpAction = HttpAction.GET, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object getById(OauthLimitRequest app);

    @ServiceMethod(method = "oauth.limit.get", version = "1.0", httpAction = HttpAction.GET, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object get(RopRequest request);

    @ServiceMethod(method = "oauth.limit.add", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object add(OauthLimitRequest app);

    @ServiceMethod(method = "oauth.limit.update", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object update(OauthLimitRequest app);

    @ServiceMethod(method = "oauth.limit.delete", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object delete(OauthLimitRequest app);
}
