package org.jeecg.modules.shoeDistribution.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.shoeDistribution.entity.ShoeDistribution;
import org.jeecg.modules.shoeDistribution.service.IShoeDistributionService;

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
 * @Description: 鞋子分配表
 * @Author: jeecg-boot
 * @Date:   2025-03-17
 * @Version: V1.0
 */
@Api(tags="鞋子分配表")
@RestController
@RequestMapping("/shoeDistribution")
@Slf4j
public class ShoeDistributionController extends JeecgController<ShoeDistribution, IShoeDistributionService> {
	@Autowired
	private IShoeDistributionService shoeDistributionService;
	
	/**
	 * 分页列表查询
	 *
	 * @param shoeDistribution
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "鞋子分配表-分页列表查询")
	@ApiOperation(value="鞋子分配表-分页列表查询", notes="鞋子分配表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ShoeDistribution>> queryPageList(ShoeDistribution shoeDistribution,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<ShoeDistribution> queryWrapper = QueryGenerator.initQueryWrapper(shoeDistribution, req.getParameterMap());
		Page<ShoeDistribution> page = new Page<ShoeDistribution>(pageNo, pageSize);
		IPage<ShoeDistribution> pageList = shoeDistributionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param shoeDistribution
	 * @return
	 */
	@AutoLog(value = "鞋子分配表-添加")
	@ApiOperation(value="鞋子分配表-添加", notes="鞋子分配表-添加")
	@RequiresPermissions("shoeDistribution:shoe_distribution:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ShoeDistribution shoeDistribution) {
		shoeDistributionService.save(shoeDistribution);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param shoeDistribution
	 * @return
	 */
	@AutoLog(value = "鞋子分配表-编辑")
	@ApiOperation(value="鞋子分配表-编辑", notes="鞋子分配表-编辑")
	@RequiresPermissions("shoeDistribution:shoe_distribution:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ShoeDistribution shoeDistribution) {
		shoeDistributionService.updateById(shoeDistribution);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "鞋子分配表-通过id删除")
	@ApiOperation(value="鞋子分配表-通过id删除", notes="鞋子分配表-通过id删除")
	@RequiresPermissions("shoeDistribution:shoe_distribution:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		shoeDistributionService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "鞋子分配表-批量删除")
	@ApiOperation(value="鞋子分配表-批量删除", notes="鞋子分配表-批量删除")
	@RequiresPermissions("shoeDistribution:shoe_distribution:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.shoeDistributionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "鞋子分配表-通过id查询")
	@ApiOperation(value="鞋子分配表-通过id查询", notes="鞋子分配表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ShoeDistribution> queryById(@RequestParam(name="id",required=true) String id) {
		ShoeDistribution shoeDistribution = shoeDistributionService.getById(id);
		if(shoeDistribution==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(shoeDistribution);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param shoeDistribution
    */
    @RequiresPermissions("shoeDistribution:shoe_distribution:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ShoeDistribution shoeDistribution) {
        return super.exportXls(request, shoeDistribution, ShoeDistribution.class, "鞋子分配表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("shoeDistribution:shoe_distribution:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ShoeDistribution.class);
    }

}
