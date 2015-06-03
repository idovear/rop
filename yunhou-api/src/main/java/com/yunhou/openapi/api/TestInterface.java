package com.yunhou.openapi.api;

import com.rop.RopRequest;
import com.rop.annotation.IgnoreSignType;
import com.rop.annotation.NeedAccessTokenType;
import com.rop.annotation.ServiceMethod;

public interface TestInterface {
    @ServiceMethod(method = "openapi.test", version = "1.0", needAccessToken = NeedAccessTokenType.YES, ignoreSign = IgnoreSignType.YES)
    Object test(RopRequest request);
}
