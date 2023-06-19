package org.cuit.fhzheng.common.constant;

import org.cuit.fhzheng.common.feign.FeignInsideAuthConfig;

/**
 * @author
 * @date 2020/12/8
 */
public interface Auth {

    String CHECK_TOKEN_URI = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/token/checkToken";

    String CHECK_RBAC_URI = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/permission/checkPermission";
}
