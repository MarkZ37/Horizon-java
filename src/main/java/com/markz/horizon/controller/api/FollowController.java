package com.markz.horizon.controller.api;


import com.markz.horizon.entity.base.BaseResponse;
import com.markz.horizon.entity.model.DeployArticleModel;
import com.markz.horizon.entity.model.WebSelfOtherUserNameModel;
import com.markz.horizon.entity.model.WebUserNameModel;
import com.markz.horizon.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/follow")
public class FollowController {

    @Autowired
    FollowService followService;

    @RequestMapping(value = "/webfollowuser", method = RequestMethod.POST)
    public BaseResponse webFollowUser(@RequestBody @Valid WebSelfOtherUserNameModel webSelfOtherUserNameModel){
        return followService.followUser(webSelfOtherUserNameModel);
    }

    @RequestMapping(value = "/isfollowjudge", method = RequestMethod.POST)
    public BaseResponse isFollowJudge(@RequestBody @Valid WebSelfOtherUserNameModel webSelfOtherUserNameModel){
        return followService.isFollowJudge(webSelfOtherUserNameModel);
    }

    @RequestMapping(value = "/webcancelfollowuser", method = RequestMethod.POST)
    public BaseResponse webCancelFollowUser(@RequestBody @Valid WebSelfOtherUserNameModel webSelfOtherUserNameModel){
        return followService.cancelFollowUser(webSelfOtherUserNameModel);
    }

    @RequestMapping(value = "/getuserfollowlist", method = RequestMethod.POST)
    public BaseResponse webGetUserFollowList(@RequestBody @Valid WebUserNameModel webUserNameModel){
        return followService.getFollowUserList(webUserNameModel);
    }
}
