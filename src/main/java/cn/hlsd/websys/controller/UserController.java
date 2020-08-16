package cn.hlsd.websys.controller;


import cn.hlsd.websys.entity.Result;
import cn.hlsd.websys.entity.User;
import cn.hlsd.websys.service.IUserService;
import cn.hlsd.websys.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ean
 * @since 2020-08-04
 */
@RestController
@RequestMapping("//user")
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    /**
     * 获取用户数组
     * @return
     */
    @ApiOperation("获取用户数组")
    @GetMapping("getList")
    public Result getList(){
        return ResultUtil.success("hello world");
    }

    /**
     * @param
     * @return
     */
    @PostMapping("insert")
    public Result insert(@RequestBody User user) {
        userService.save(user);
        logger.info("用户创建成功< "+user.toString()+" >");
        return ResultUtil.success(userService.getById(user));
    }

}
