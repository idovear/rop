package com.yunhou.openapi.api.config;

import com.rop.RopRequest;
import com.rop.annotation.HttpAction;
import com.rop.annotation.IgnoreSignType;
import com.rop.annotation.NeedAccessTokenType;
import com.rop.annotation.ServiceMethod;
import com.yunhou.openapi.request.config.OauthLevelConfigRequest;

/**
 * 
 * 应用等级配置<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月8日 下午3:27:59
 * @version 1.0
 * @since JDK 1.7
 */
public interface OauthLevelConfigInterface {

    @ServiceMethod(method = "oauth.level.list", version = "1.0", httpAction = HttpAction.GET, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object getLevels(RopRequest request);

    @ServiceMethod(method = "oauth.level.get.id", version = "1.0", httpAction = HttpAction.GET, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object getById(OauthLevelConfigRequest config);

    @ServiceMethod(method = "oauth.level.get", version = "1.0", httpAction = HttpAction.GET, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object get(RopRequest request);

    @ServiceMethod(method = "oauth.level.add", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object add(OauthLevelConfigRequest config);

    @ServiceMethod(method = "oauth.level.update", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object update(OauthLevelConfigRequest config);

    @ServiceMethod(method = "oauth.level.delete", version = "1.0", httpAction = HttpAction.POST, needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object delete(OauthLevelConfigRequest config);
}
