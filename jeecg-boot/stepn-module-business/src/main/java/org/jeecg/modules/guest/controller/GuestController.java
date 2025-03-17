package org.jeecg.modules.guest.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.guest.entity.Guest;
import org.jeecg.modules.guest.service.IGuestService;

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
 * @Description: 租客资料
 * @Author: jeecg-boot
 * @Date:   2025-03-17
 * @Version: V1.0
 */
@Api(tags="租客资料")
@RestController
@RequestMapping("/guest")
@Slf4j
public class GuestController extends JeecgController<Guest, IGuestService> {
	@Autowired
	private IGuestService guestService;
	
	/**
	 * 分页列表查询
	 *
	 * @param guest
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "租客资料-分页列表查询")
	@ApiOperation(value="租客资料-分页列表查询", notes="租客资料-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Guest>> queryPageList(Guest guest,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<Guest> queryWrapper = QueryGenerator.initQueryWrapper(guest, req.getParameterMap());
		Page<Guest> page = new Page<Guest>(pageNo, pageSize);
		IPage<Guest> pageList = guestService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param guest
	 * @return
	 */
	@AutoLog(value = "租客资料-添加")
	@ApiOperation(value="租客资料-添加", notes="租客资料-添加")
	@RequiresPermissions("modules.guest:guest:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Guest guest) {
		guestService.save(guest);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param guest
	 * @return
	 */
	@AutoLog(value = "租客资料-编辑")
	@ApiOperation(value="租客资料-编辑", notes="租客资料-编辑")
	@RequiresPermissions("modules.guest:guest:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Guest guest) {
		guestService.updateById(guest);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "租客资料-通过id删除")
	@ApiOperation(value="租客资料-通过id删除", notes="租客资料-通过id删除")
	@RequiresPermissions("modules.guest:guest:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		guestService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "租客资料-批量删除")
	@ApiOperation(value="租客资料-批量删除", notes="租客资料-批量删除")
	@RequiresPermissions("modules.guest:guest:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.guestService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "租客资料-通过id查询")
	@ApiOperation(value="租客资料-通过id查询", notes="租客资料-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Guest> queryById(@RequestParam(name="id",required=true) String id) {
		Guest guest = guestService.getById(id);
		if(guest==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(guest);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param guest
    */
    @RequiresPermissions("modules.guest:guest:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Guest guest) {
        return super.exportXls(request, guest, Guest.class, "租客资料");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("modules.guest:guest:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Guest.class);
    }

}
