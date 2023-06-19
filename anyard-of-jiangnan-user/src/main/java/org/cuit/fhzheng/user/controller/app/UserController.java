package org.cuit.fhzheng.user.controller.app;

import org.cuit.fhzheng.api.auth.bo.UserInfoInTokenBO;
import org.cuit.fhzheng.api.user.vo.UserApiVO;
import org.cuit.fhzheng.common.response.ResponseEnum;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.cuit.fhzheng.user.model.User;
import org.cuit.fhzheng.user.service.UserService;
import org.cuit.fhzheng.user.vo.UserSimpleInfoVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 用户地址
 *
 * @author  
 * @date 2020-12-07 15:50:02
 */
@RestController("appUserController")
@RequestMapping("/a/user")
@Tag(name = "app-用户信息")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/simple_info")
    @Operation(summary = "用户头像昵称" , description = "用户头像昵称")
    public ServerResponseEntity<UserSimpleInfoVO> getByAddrId() {
        Long userId = AuthUserContext.get().getUserId();

        UserApiVO userApiVO = userService.getByUserId(userId);
        UserSimpleInfoVO userSimpleInfoVO = new UserSimpleInfoVO();
        userSimpleInfoVO.setNickName(userApiVO.getNickName());
        userSimpleInfoVO.setPic(userApiVO.getPic());

        return ServerResponseEntity.success(userSimpleInfoVO);
    }


    @GetMapping("/ma/user_detail_info")
    @Operation(summary = "获取用户详细信息" , description = "返回用户详细信息")
    public ServerResponseEntity<UserApiVO> getUserDetailInfo() {
        UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
        if (userInfoInTokenBO == null) {
            return ServerResponseEntity.fail(ResponseEnum.CLEAN_TOKEN);
        }
        Long userId = userInfoInTokenBO.getUserId();
        UserApiVO userApiVO = userService.getByUserId(userId);
        return ServerResponseEntity.success(userApiVO);
    }

    @PostMapping ("/ma/update_user")
    @Operation(summary = "更新用户信息")
    public ServerResponseEntity<Void> updateUser(@RequestBody UserApiVO userApiVO) {
        Long userId = AuthUserContext.get().getUserId();
        UserApiVO byUserId = userService.getByUserId(userId);
        User user = new User();
        user.setUserId(userId);
        user.setNickName(Objects.isNull(userApiVO.getNickName())? byUserId.getNickName() : userApiVO.getNickName());
        user.setPic(Objects.isNull(userApiVO.getPic())? byUserId.getPic() : userApiVO.getPic());
        userService.update(user);
        return ServerResponseEntity.success();
    }
}
