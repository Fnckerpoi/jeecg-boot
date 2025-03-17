import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '多租户隔离',
    align:"center",
    dataIndex: 'tenantId'
   },
   {
    title: '鞋主名称',
    align:"center",
    dataIndex: 'hausName'
   },
   {
    title: '分成模式',
    align:"center",
    dataIndex: 'sharingModel_dictText'
   },
   {
    title: '收益分成系数',
    align:"center",
    dataIndex: 'coefficient'
   },
   {
    title: '收件邮箱',
    align:"center",
    dataIndex: 'mailbox'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '多租户隔离',
    field: 'tenantId',
    component: 'InputNumber',
  },
  {
    label: '鞋主名称',
    field: 'hausName',
    component: 'Input',
  },
  {
    label: '分成模式',
    field: 'sharingModel',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"sharing_method",
        type: "radio"
     },
  },
  {
    label: '收益分成系数',
    field: 'coefficient',
    component: 'InputNumber',
  },
  {
    label: '收件邮箱',
    field: 'mailbox',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

// 高级查询数据
export const superQuerySchema = {
  tenantId: {title: '多租户隔离',order: 0,view: 'number', type: 'number',},
  hausName: {title: '鞋主名称',order: 1,view: 'text', type: 'string',},
  sharingModel: {title: '分成模式',order: 2,view: 'radio', type: 'string',dictCode: 'sharing_method',},
  coefficient: {title: '收益分成系数',order: 3,view: 'number', type: 'number',},
  mailbox: {title: '收件邮箱',order: 4,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}