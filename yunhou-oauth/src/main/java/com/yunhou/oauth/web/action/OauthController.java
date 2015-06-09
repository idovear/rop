package com.yunhou.oauth.web.action;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yunhou.oauth.model.Authorize;
import com.yunhou.oauth.model.AuthorizeCode;
import com.yunhou.oauth.model.AuthorizeError;
import com.yunhou.oauth.model.AuthorizeErrorCode;
import com.yunhou.oauth.model.Token;
import com.yunhou.oauth.redis.OauthRedis;
import com.yunhou.openapi.common.model.AppSecret;
import com.yunhou.openapi.common.model.TokenInfo;
import com.yunhou.openapi.common.model.User;
import com.yunhou.openapi.common.util.Base64Util;
import com.yunhou.openapi.common.util.MD5Util;

/**
 * 
 * 页面加载<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年4月29日 下午3:31:12
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
public class OauthController extends BaseController {
    @Autowired
    private OauthRedis oauthRedis;

    /**
     * 
     * 1.请求授权用户登录<br/>
     * 
     * @author 何冰(hebing@bubugao.com)
     * @date: 2015年6月2日 下午5:26:09
     * @version 1.0
     * 
     * @param authorize
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/authorize")
    public ModelAndView authorize(Authorize authorize, HttpServletRequest request, HttpServletResponse response) {
        if (!isValidAuthorize(authorize)) {
            ModelAndView mv = new ModelAndView("authorizeError");
            return mv;
        }
        AppSecret appSecret = oauthRedis.getAppByAppKey(authorize.getApp_key());
        if (appSecret == null) {
            ModelAndView mv = new ModelAndView("authorizeError");
            request.setAttribute("error", "应用不存在!");
            return mv;
        }
        if (!authorize.getRedirect_uri().contains(appSecret.getRedirect_uri())) {
            ModelAndView mv = new ModelAndView("authorizeError");
            request.setAttribute("error", "回调地址错误!");
            return mv;
        }

        ModelAndView mv = new ModelAndView("authorize");
        mv.addObject("authorize", authorize);
        return mv;
    }

    /**
     * 
     * 2.用户登录. <br/>
     * 登录成功返回 sessionId
     * 
     * @author 何冰(hebing@bubugao.com)
     * @date: 2015年6月2日 下午5:26:30
     * @version 1.0
     * 
     * @param authorize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/authorize/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String login(Authorize authorize) {
        AuthorizeCode result = new AuthorizeCode();
        try {
            if (!isValidAuthorize(authorize)) {
                logger.error("用户登录参数验证失败");
                result.setError("传入参数异常");
                result.setError_description("传入参数异常");
                return new JSONObject(result).toString();
            }
            // TODO 进行登录 成功之后获取sessionId返回
            if (StringUtils.isNotBlank(authorize.getUsername()) && StringUtils.isNotBlank(authorize.getPassword())) {
                result.setCode(authorize.getUsername() + System.currentTimeMillis());// 获取登录成功后的sessionId
            } else {
                result.setError("用户名或者密码错误");
                result.setError_description("用户名或者密码错误");
            }
            return new JSONObject(result).toString();
        } catch (Exception e) {
            logger.error("授权登录异常", e);
            result.setError("授权登录异常");
            result.setError_description("授权登录异常");
            return new JSONObject(result).toString();
        }
    }

    /**
     * 
     * 用户授权.成功后跳转 <br/>
     * 如果同意授权，判断sessionId是否为登录状态，成功返回code
     * 
     * @author 何冰(hebing@bubugao.com)
     * @date: 2015年6月2日 下午5:26:47
     * @version 1.0
     * 
     * @param authorize
     * @param attr
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/authorize/oauth")
    public String oauth(String appkey, String sessionId, Boolean authorize, String redirect_uri) {
        String redirect = "redirect:" + redirect_uri;
        if (!redirect.contains("?")) {
            redirect += "?";
        }
        if (redirect.indexOf("?") + 1 != redirect.length()) {
            redirect += "&";
        }
        if (!authorize) {
            redirect += "error=access_denied&error_description=authorize reject";
        } else {
            if (StringUtils.isNotBlank(sessionId)) {// 登录成功 TODO
                String str = appkey + sessionId + UUID.randomUUID().toString() + System.currentTimeMillis();
                String code = Base64Util.getBase64(MD5Util.GetMD5Code(str));
                redirect += "code=" + code;
                User user = new User();// TODO 登录成功获取用户信息
                String username = "idovear" + System.currentTimeMillis();
                user.setUserId(username);
                user.setUsername(username);
                oauthRedis.putCode(appkey, code, user);
            } else {// 登录失败
                redirect += "error=sessionid expires&error_description=sessionid not exist or expires";
            }
        }
        return redirect;
    }

    /**
     * 
     * 获取访问令牌 <br/>
     * 
     * @author 何冰(hebing@bubugao.com)
     * @date: 2015年6月3日 上午12:16:55
     * @version 1.0
     * 
     * @param authorize code,grant_type,app_key,app_secret,redirect_uri
     * @return
     */
    @RequestMapping(value = "/access_token", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String token(Authorize authorize) {
        /* 参数合法性验证 */
        if (!isValidToken(authorize)) {
            AuthorizeError errorCode = new AuthorizeError(AuthorizeErrorCode.Error_20001);
            return new JSONObject(errorCode).toString();
        }
        /* 应用验证验证 appkey secret redirect_uri */
        AppSecret appSecret = oauthRedis.getAppByAppKey(authorize.getApp_key());
        if (appSecret == null || !appSecret.getAppSecret().equals(authorize.getApp_secret())) {
            return new JSONObject(new AuthorizeError(AuthorizeErrorCode.Error_20002)).toString();
        }
        if (!authorize.getRedirect_uri().contains(appSecret.getRedirect_uri())) {
            return new JSONObject(new AuthorizeError(AuthorizeErrorCode.Error_20003)).toString();
        }
        /* code识别 */
        User user = oauthRedis.getUserByCode(authorize.getApp_key(), authorize.getCode());
        if (user == null) {
            return new JSONObject(new AuthorizeError(AuthorizeErrorCode.Error_20004)).toString();
        }// TODO 判断该用户 和 appkey是否存在token 存在token不用重新生成token
        Token token = new Token();
        String access_token = "";
        String refresh_token = "";

        String str = authorize.getCode() + UUID.randomUUID().toString() + System.currentTimeMillis() + authorize.getApp_key()
                + authorize.getRedirect_uri();
        access_token = "2.00" + MD5Util.GetMD5Code(MD5Util.GetMD5Code(str)).replace("=", "");
        refresh_token = MD5Util.GetMD5Code(access_token);

        token.setUserId(user.getUserId());
        token.setUsername(user.getUsername());
        token.setAccess_token(access_token);
        token.setExpires_in(7 * 24 * 60 * 60);
        token.setRefresh_token(refresh_token);
        token.setRe_expires_in(30 * 24 * 60 * 60);
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setAppKey(authorize.getApp_key());
        tokenInfo.setUserId(user.getUserId());
        oauthRedis.putToken(access_token, 7 * 24 * 60 * 60, tokenInfo);
        return new JSONObject(token).toString();
    }

    private boolean isValidToken(Authorize authorize) {
        if (StringUtils.isBlank(authorize.getApp_key())) {
            return false;
        }
        if (StringUtils.isBlank(authorize.getApp_secret())) {
            return false;
        }
        if (StringUtils.isBlank(authorize.getGrant_type())) {
            return false;
        }
        if (StringUtils.isBlank(authorize.getCode())) {
            return false;
        }
        if (StringUtils.isBlank(authorize.getRedirect_uri())) {
            return false;
        }
        return true;
    }

    private boolean isValidAuthorize(Authorize authorize) {
        if (StringUtils.isBlank(authorize.getApp_key())) {
            return false;
        }
        if (StringUtils.isBlank(authorize.getResponse_type())) {
            return false;
        }
        if (StringUtils.isBlank(authorize.getRedirect_uri())) {
            return false;
        }
        return true;
    }

}
