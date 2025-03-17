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
    title: '鞋子编号',
    align:"center",
    dataIndex: 'shoeNumber'
   },
   {
    title: '鞋子种类',
    align:"center",
    dataIndex: 'shoesType_dictText'
   },
   {
    title: '到期日期',
    align:"center",
    dataIndex: 'dateDue'
   },
   {
    title: '租鞋人关联',
    align:"center",
    dataIndex: 'renterId'
   },
   {
    title: '鞋主关联',
    align:"center",
    dataIndex: 'shoeOwnerId'
   },
   {
    title: '账号邮箱',
    align:"center",
    dataIndex: 'accountEmail'
   },
   {
    title: 'GGT收益',
    align:"center",
    dataIndex: 'ggtRevenue'
   },
   {
    title: '结算金额',
    align:"center",
    dataIndex: 'settlementAmount'
   },
   {
    title: '是否租赁结束',
    align:"center",
    dataIndex: 'isEndString'
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
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入租户隔离!'},
          ];
     },
  },
  {
    label: '鞋子编号',
    field: 'shoeNumber',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入鞋子编号!'},
          ];
     },
  },
  {
    label: '鞋子种类',
    field: 'shoesType',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"shoe_type",
        type: "radio"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入鞋子种类!'},
          ];
     },
  },
  {
    label: '到期日期',
    field: 'dateDue',
    component: 'DatePicker',
    componentProps: {
       showTime: true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入到期日期!'},
          ];
     },
  },
  {
    label: '租鞋人关联',
    field: 'renterId',
    component: 'JPopup',
    componentProps: ({ formActionType }) => {
        const {setFieldsValue} = formActionType;
        return{
            setFieldsValue:setFieldsValue,
            code:"",
            fieldConfig: [
                { source: '', target: '' },
            ],
            multi:true
        }
    },

    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入租鞋人关联!'},
          ];
     },
  },
  {
    label: '鞋主关联',
    field: 'shoeOwnerId',
    component: 'JPopup',
    componentProps: ({ formActionType }) => {
        const {setFieldsValue} = formActionType;
        return{
            setFieldsValue:setFieldsValue,
            code:"",
            fieldConfig: [
                { source: '', target: '' },
            ],
            multi:true
        }
    },

    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入鞋主关联!'},
          ];
     },
  },
  {
    label: '账号邮箱',
    field: 'accountEmail',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入账号邮箱!'},
          ];
     },
  },
  {
    label: 'GGT收益',
    field: 'ggtRevenue',
    component: 'InputNumber',
  },
  {
    label: '结算金额',
    field: 'settlementAmount',
    component: 'InputNumber',
  },
  {
    label: '是否租赁结束',
    field: 'isEndString',
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
  shoeNumber: {title: '鞋子编号',order: 1,view: 'text', type: 'string',},
  shoesType: {title: '鞋子种类',order: 2,view: 'radio', type: 'string',dictCode: 'shoe_type',},
  dateDue: {title: '到期日期',order: 3,view: 'datetime', type: 'string',},
  renterId: {title: '租鞋人关联',order: 4,view: 'popup', type: 'string',code: '', orgFields: '', destFields: 'renterId', popupMulti: false,},
  shoeOwnerId: {title: '鞋主关联',order: 5,view: 'popup', type: 'string',code: '', orgFields: '', destFields: 'shoeOwnerId', popupMulti: false,},
  accountEmail: {title: '账号邮箱',order: 6,view: 'text', type: 'string',},
  ggtRevenue: {title: 'GGT收益',order: 7,view: 'number', type: 'number',},
  settlementAmount: {title: '结算金额',order: 8,view: 'number', type: 'number',},
  isEnd: {title: '是否租赁结束',order: 9,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}