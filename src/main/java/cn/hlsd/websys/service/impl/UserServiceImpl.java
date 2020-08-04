package cn.hlsd.websys.service.impl;

import cn.hlsd.websys.entity.User;
import cn.hlsd.websys.mapper.UserMapper;
import cn.hlsd.websys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ean
 * @since 2020-08-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
