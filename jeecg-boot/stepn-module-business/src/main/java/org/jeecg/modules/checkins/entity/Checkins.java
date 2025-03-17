package org.jeecg.modules.checkins.entity;

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
 * @Description: 打卡记录表
 * @Author: jeecg-boot
 * @Date:   2025-03-17
 * @Version: V1.0
 */
@Data
@TableName("checkins")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="checkins对象", description="打卡记录表")
public class Checkins implements Serializable {
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
	/**租户隔离*/
	@Excel(name = "租户隔离", width = 15)
    @ApiModelProperty(value = "租户隔离")
    private java.lang.Integer tenantId;
	/**鞋子关联*/
	@Excel(name = "鞋子关联", width = 15)
    @ApiModelProperty(value = "鞋子关联")
    private java.lang.String shoeId;
	/**截图*/
	@Excel(name = "截图", width = 15)
    @ApiModelProperty(value = "截图")
    private java.lang.String screenshot;
	/**GGT产出*/
	@Excel(name = "GGT产出", width = 15)
    @ApiModelProperty(value = "GGT产出")
    private java.math.BigDecimal output;
	/**打卡时间*/
	@Excel(name = "打卡时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "打卡时间")
    private java.util.Date signTime;
	/**分组*/
	@Excel(name = "分组", width = 15)
    @ApiModelProperty(value = "分组")
    private java.lang.String grouping;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**是否结束*/
	@Excel(name = "是否结束", width = 15)
    private transient java.lang.String idEndString;

    private byte[] idEnd;

    public byte[] getIdEnd(){
        if(idEndString==null){
            return null;
        }
        try {
            return idEndString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getIdEndString(){
        if(idEnd==null || idEnd.length==0){
            return "";
        }
        try {
            return new String(idEnd,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
