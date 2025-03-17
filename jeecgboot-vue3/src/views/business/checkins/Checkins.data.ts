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
    title: '鞋子关联',
    align:"center",
    dataIndex: 'shoeId'
   },
   {
    title: '截图',
    align:"center",
    dataIndex: 'screenshot'
   },
   {
    title: 'GGT产出',
    align:"center",
    dataIndex: 'output'
   },
   {
    title: '打卡时间',
    align:"center",
    dataIndex: 'signTime'
   },
   {
    title: '分组',
    align:"center",
    dataIndex: 'grouping'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'remark'
   },
   {
    title: '是否结束',
    align:"center",
    dataIndex: 'idEndString'
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
    label: '鞋子关联',
    field: 'shoeId',
    component: 'Input',
  },
  {
    label: '截图',
    field: 'screenshot',
    component: 'Input',
  },
  {
    label: 'GGT产出',
    field: 'output',
    component: 'InputNumber',
  },
  {
    label: '打卡时间',
    field: 'signTime',
    component: 'DatePicker',
    componentProps: {
       showTime: true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
  {
    label: '分组',
    field: 'grouping',
    component: 'Input',
  },
  {
    label: '备注',
    field: 'remark',
    component: 'Input',
  },
  {
    label: '是否结束',
    field: 'idEndString',
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
  shoeId: {title: '鞋子关联',order: 1,view: 'link_table', type: 'string',},
  screenshot: {title: '截图',order: 2,view: 'text', type: 'string',},
  output: {title: 'GGT产出',order: 3,view: 'number', type: 'number',},
  signTime: {title: '打卡时间',order: 4,view: 'datetime', type: 'string',},
  grouping: {title: '分组',order: 5,view: 'text', type: 'string',},
  remark: {title: '备注',order: 6,view: 'text', type: 'string',},
  idEnd: {title: '是否结束',order: 7,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}