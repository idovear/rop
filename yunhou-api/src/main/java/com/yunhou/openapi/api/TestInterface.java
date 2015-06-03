package com.yunhou.openapi.api;

import com.rop.RopRequest;
import com.rop.annotation.IgnoreSignType;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;

public interface TestInterface {
    @ServiceMethod(method = "openapi.test", version = "1.0", needInSession = NeedInSessionType.YES, ignoreSign = IgnoreSignType.YES)
    Object test(RopRequest request);
}
