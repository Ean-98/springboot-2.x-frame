package cn.hlsd.websys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userGender;

    private String userTel;

    private String userEmail;

    private String userLogo;

    @ApiModelProperty(value = "用户名")
    private String uAcco;

    @ApiModelProperty(value = "密码")
    private String uPass;

    @ApiModelProperty(value = "上级a，这里指的是代理商。-1代表没绑定，否则表示是代理商的用户id")
    private Integer userUpperA;

    @ApiModelProperty(value = "余额,如果提现了需要减去，这个字段普通用户跟代理商都会有，分销的时候是加到这个字段，")
    private Integer userBalance;

    @ApiModelProperty(value = " 总余额，不管有没有提现，都不会减少。-代理商收入跟分销收入都会加到这里")
    private Integer userTotalBalance;

    @ApiModelProperty(value = "上级b，这里指的是代理商。-1代表没绑定，否则表示是代理商的用户id")
    private Integer userUpperB;

    private Long createtime;

    @ApiModelProperty(value = " 销售额度，代理商才会有，普通用户可以不用考虑该字段，代理商收入会添加到这个字段")
    private Integer userSaleAmount;

    @ApiModelProperty(value = " 二维码")
    private String userCode;


}
