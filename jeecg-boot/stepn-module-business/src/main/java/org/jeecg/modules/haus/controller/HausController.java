package org.jeecg.modules.haus.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.haus.entity.Haus;
import org.jeecg.modules.haus.service.IHausService;

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
 * @Description: 鞋主资料维护
 * @Author: jeecg-boot
 * @Date:   2025-03-17
 * @Version: V1.0
 */
@Api(tags="鞋主资料维护")
@RestController
@RequestMapping("/haus")
@Slf4j
public class HausController extends JeecgController<Haus, IHausService> {
	@Autowired
	private IHausService hausService;
	
	/**
	 * 分页列表查询
	 *
	 * @param haus
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "鞋主资料维护-分页列表查询")
	@ApiOperation(value="鞋主资料维护-分页列表查询", notes="鞋主资料维护-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Haus>> queryPageList(Haus haus,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<Haus> queryWrapper = QueryGenerator.initQueryWrapper(haus, req.getParameterMap());
		Page<Haus> page = new Page<Haus>(pageNo, pageSize);
		IPage<Haus> pageList = hausService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param haus
	 * @return
	 */
	@AutoLog(value = "鞋主资料维护-添加")
	@ApiOperation(value="鞋主资料维护-添加", notes="鞋主资料维护-添加")
	@RequiresPermissions("haus:haus:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Haus haus) {
		hausService.save(haus);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param haus
	 * @return
	 */
	@AutoLog(value = "鞋主资料维护-编辑")
	@ApiOperation(value="鞋主资料维护-编辑", notes="鞋主资料维护-编辑")
	@RequiresPermissions("haus:haus:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Haus haus) {
		hausService.updateById(haus);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "鞋主资料维护-通过id删除")
	@ApiOperation(value="鞋主资料维护-通过id删除", notes="鞋主资料维护-通过id删除")
	@RequiresPermissions("haus:haus:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		hausService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "鞋主资料维护-批量删除")
	@ApiOperation(value="鞋主资料维护-批量删除", notes="鞋主资料维护-批量删除")
	@RequiresPermissions("haus:haus:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hausService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "鞋主资料维护-通过id查询")
	@ApiOperation(value="鞋主资料维护-通过id查询", notes="鞋主资料维护-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Haus> queryById(@RequestParam(name="id",required=true) String id) {
		Haus haus = hausService.getById(id);
		if(haus==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(haus);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param haus
    */
    @RequiresPermissions("haus:haus:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Haus haus) {
        return super.exportXls(request, haus, Haus.class, "鞋主资料维护");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("haus:haus:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Haus.class);
    }

}
