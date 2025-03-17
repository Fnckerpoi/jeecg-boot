package org.jeecg.modules.shoeDistribution.entity;

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
 * @Description: 鞋子分配表
 * @Author: jeecg-boot
 * @Date:   2025-03-17
 * @Version: V1.0
 */
@Data
@TableName("shoe_distribution")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="shoe_distribution对象", description="鞋子分配表")
public class ShoeDistribution implements Serializable {
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
	/**鞋子编号*/
	@Excel(name = "鞋子编号", width = 15)
    @ApiModelProperty(value = "鞋子编号")
    private java.lang.String shoeNumber;
	/**鞋子种类*/
	@Excel(name = "鞋子种类", width = 15, dicCode = "shoe_type")
	@Dict(dicCode = "shoe_type")
    @ApiModelProperty(value = "鞋子种类")
    private java.lang.String shoesType;
	/**到期日期*/
	@Excel(name = "到期日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "到期日期")
    private java.util.Date dateDue;
	/**租鞋人关联*/
	@Excel(name = "租鞋人关联", width = 15)
    @ApiModelProperty(value = "租鞋人关联")
    private java.lang.String renterId;
	/**鞋主关联*/
	@Excel(name = "鞋主关联", width = 15)
    @ApiModelProperty(value = "鞋主关联")
    private java.lang.String shoeOwnerId;
	/**账号邮箱*/
	@Excel(name = "账号邮箱", width = 15)
    @ApiModelProperty(value = "账号邮箱")
    private java.lang.String accountEmail;
	/**GGT收益*/
	@Excel(name = "GGT收益", width = 15)
    @ApiModelProperty(value = "GGT收益")
    private java.math.BigDecimal ggtRevenue;
	/**结算金额*/
	@Excel(name = "结算金额", width = 15)
    @ApiModelProperty(value = "结算金额")
    private java.math.BigDecimal settlementAmount;
	/**是否租赁结束*/
	@Excel(name = "是否租赁结束", width = 15)
    private transient java.lang.String isEndString;

    private byte[] isEnd;

    public byte[] getIsEnd(){
        if(isEndString==null){
            return null;
        }
        try {
            return isEndString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getIsEndString(){
        if(isEnd==null || isEnd.length==0){
            return "";
        }
        try {
            return new String(isEnd,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
