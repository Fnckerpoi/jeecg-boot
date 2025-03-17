package org.jeecg.modules.checkins.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.checkins.entity.Checkins;
import org.jeecg.modules.checkins.service.ICheckinsService;

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
 * @Description: 打卡记录表
 * @Author: jeecg-boot
 * @Date:   2025-03-17
 * @Version: V1.0
 */
@Api(tags="打卡记录表")
@RestController
@RequestMapping("/checkins")
@Slf4j
public class CheckinsController extends JeecgController<Checkins, ICheckinsService> {
	@Autowired
	private ICheckinsService checkinsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param checkins
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "打卡记录表-分页列表查询")
	@ApiOperation(value="打卡记录表-分页列表查询", notes="打卡记录表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Checkins>> queryPageList(Checkins checkins,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<Checkins> queryWrapper = QueryGenerator.initQueryWrapper(checkins, req.getParameterMap());
		Page<Checkins> page = new Page<Checkins>(pageNo, pageSize);
		IPage<Checkins> pageList = checkinsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param checkins
	 * @return
	 */
	@AutoLog(value = "打卡记录表-添加")
	@ApiOperation(value="打卡记录表-添加", notes="打卡记录表-添加")
	@RequiresPermissions("checkins:checkins:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Checkins checkins) {
		checkinsService.save(checkins);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param checkins
	 * @return
	 */
	@AutoLog(value = "打卡记录表-编辑")
	@ApiOperation(value="打卡记录表-编辑", notes="打卡记录表-编辑")
	@RequiresPermissions("checkins:checkins:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Checkins checkins) {
		checkinsService.updateById(checkins);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "打卡记录表-通过id删除")
	@ApiOperation(value="打卡记录表-通过id删除", notes="打卡记录表-通过id删除")
	@RequiresPermissions("checkins:checkins:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		checkinsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "打卡记录表-批量删除")
	@ApiOperation(value="打卡记录表-批量删除", notes="打卡记录表-批量删除")
	@RequiresPermissions("checkins:checkins:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkinsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "打卡记录表-通过id查询")
	@ApiOperation(value="打卡记录表-通过id查询", notes="打卡记录表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Checkins> queryById(@RequestParam(name="id",required=true) String id) {
		Checkins checkins = checkinsService.getById(id);
		if(checkins==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkins);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkins
    */
    @RequiresPermissions("checkins:checkins:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Checkins checkins) {
        return super.exportXls(request, checkins, Checkins.class, "打卡记录表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("checkins:checkins:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Checkins.class);
    }

}
