package cn.hlsd.websys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ean
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("_user_role")
@ApiModel(value="UserRole对象", description="")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_role_id", type = IdType.AUTO)
    private Integer userRoleId;

    private Integer userId;

    private Integer roleId;

    private String userTitle;

    private String userAvatar;

    private String roleTitle;

    private Integer status;


}
