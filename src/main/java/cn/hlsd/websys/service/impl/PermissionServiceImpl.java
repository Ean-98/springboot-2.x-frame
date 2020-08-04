package cn.hlsd.websys.service.impl;

import cn.hlsd.websys.entity.Permission;
import cn.hlsd.websys.mapper.PermissionMapper;
import cn.hlsd.websys.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
