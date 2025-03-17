import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '租户隔离',
    align:"center",
    dataIndex: 'tenantId'
   },
   {
    title: '租鞋人',
    align:"center",
    dataIndex: 'renter'
   },
   {
    title: '邮箱',
    align:"center",
    dataIndex: 'mailbox'
   },
   {
    title: 'SOL钱包地址',
    align:"center",
    dataIndex: 'solAddress'
   },
   {
    title: 'POL钱包地址',
    align:"center",
    dataIndex: 'polAddress'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '租户隔离',
    field: 'tenantId',
    component: 'InputNumber',
  },
  {
    label: '租鞋人',
    field: 'renter',
    component: 'Input',
  },
  {
    label: '邮箱',
    field: 'mailbox',
    component: 'Input',
  },
  {
    label: 'SOL钱包地址',
    field: 'solAddress',
    component: 'Input',
  },
  {
    label: 'POL钱包地址',
    field: 'polAddress',
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
  tenantId: {title: '租户隔离',order: 0,view: 'number', type: 'number',},
  renter: {title: '租鞋人',order: 1,view: 'text', type: 'string',},
  mailbox: {title: '邮箱',order: 2,view: 'text', type: 'string',},
  solAddress: {title: 'SOL钱包地址',order: 3,view: 'text', type: 'string',},
  polAddress: {title: 'POL钱包地址',order: 4,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}