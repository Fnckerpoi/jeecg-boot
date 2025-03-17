package org.jeecg.modules.haus.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 鞋主资料维护
 * @Author: jeecg-boot
 * @Date:   2025-03-17
 * @Version: V1.0
 */
@Data
@TableName("haus")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="haus对象", description="鞋主资料维护")
public class Haus implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**多租户隔离*/
	@Excel(name = "多租户隔离", width = 15)
    @ApiModelProperty(value = "多租户隔离")
    private java.lang.Integer tenantId;
	/**鞋主名称*/
	@Excel(name = "鞋主名称", width = 15)
    @ApiModelProperty(value = "鞋主名称")
    private java.lang.String hausName;
	/**分成模式*/
	@Excel(name = "分成模式", width = 15, dicCode = "sharing_method")
	@Dict(dicCode = "sharing_method")
    @ApiModelProperty(value = "分成模式")
    private java.lang.String sharingModel;
	/**收益分成系数*/
	@Excel(name = "收益分成系数", width = 15)
    @ApiModelProperty(value = "收益分成系数")
    private java.lang.Double coefficient;
	/**收件邮箱*/
	@Excel(name = "收件邮箱", width = 15)
    @ApiModelProperty(value = "收件邮箱")
    private java.lang.String mailbox;
}
