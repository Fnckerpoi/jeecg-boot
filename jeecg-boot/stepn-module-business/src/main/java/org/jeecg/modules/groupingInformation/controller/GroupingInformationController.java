package org.jeecg.modules.groupingInformation.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.groupingInformation.entity.GroupingInformation;
import org.jeecg.modules.groupingInformation.service.IGroupingInformationService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 鞋子分配按组别定义分组
 * @Author: jeecg-boot
 * @Date:   2025-03-17
 * @Version: V1.0
 */
@Api(tags="鞋子分配按组别定义分组")
@RestController
@RequestMapping("/groupingInformation")
@Slf4j
public class GroupingInformationController extends JeecgController<GroupingInformation, IGroupingInformationService> {
	@Autowired
	private IGroupingInformationService groupingInformationService;
	
	/**
	 * 分页列表查询
	 *
	 * @param groupingInformation
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "鞋子分配按组别定义分组-分页列表查询")
	@ApiOperation(value="鞋子分配按组别定义分组-分页列表查询", notes="鞋子分配按组别定义分组-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<GroupingInformation>> queryPageList(GroupingInformation groupingInformation,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<GroupingInformation> queryWrapper = QueryGenerator.initQueryWrapper(groupingInformation, req.getParameterMap());
		Page<GroupingInformation> page = new Page<GroupingInformation>(pageNo, pageSize);
		IPage<GroupingInformation> pageList = groupingInformationService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param groupingInformation
	 * @return
	 */
	@AutoLog(value = "鞋子分配按组别定义分组-添加")
	@ApiOperation(value="鞋子分配按组别定义分组-添加", notes="鞋子分配按组别定义分组-添加")
	@RequiresPermissions("groupingInformation:grouping_information:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody GroupingInformation groupingInformation) {
		groupingInformationService.save(groupingInformation);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param groupingInformation
	 * @return
	 */
	@AutoLog(value = "鞋子分配按组别定义分组-编辑")
	@ApiOperation(value="鞋子分配按组别定义分组-编辑", notes="鞋子分配按组别定义分组-编辑")
	@RequiresPermissions("groupingInformation:grouping_information:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody GroupingInformation groupingInformation) {
		groupingInformationService.updateById(groupingInformation);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "鞋子分配按组别定义分组-通过id删除")
	@ApiOperation(value="鞋子分配按组别定义分组-通过id删除", notes="鞋子分配按组别定义分组-通过id删除")
	@RequiresPermissions("groupingInformation:grouping_information:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		groupingInformationService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "鞋子分配按组别定义分组-批量删除")
	@ApiOperation(value="鞋子分配按组别定义分组-批量删除", notes="鞋子分配按组别定义分组-批量删除")
	@RequiresPermissions("groupingInformation:grouping_information:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.groupingInformationService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "鞋子分配按组别定义分组-通过id查询")
	@ApiOperation(value="鞋子分配按组别定义分组-通过id查询", notes="鞋子分配按组别定义分组-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<GroupingInformation> queryById(@RequestParam(name="id",required=true) String id) {
		GroupingInformation groupingInformation = groupingInformationService.getById(id);
		if(groupingInformation==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(groupingInformation);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param groupingInformation
    */
    @RequiresPermissions("groupingInformation:grouping_information:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, GroupingInformation groupingInformation) {
        return super.exportXls(request, groupingInformation, GroupingInformation.class, "鞋子分配按组别定义分组");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("groupingInformation:grouping_information:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, GroupingInformation.class);
    }

}
