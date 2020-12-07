package com.atguigu.educms.controller;


import com.atguigu.commonutils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author hdoubleq
 * @since 2020-11-30
 */
@Api(description = "后台管理")
@RestController
@RequestMapping("/educms/banneradmin")
//@CrossOrigin
public class BannerAdminController {
    @Autowired
    CrmBannerService bannerService;

    //分页查询
    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page,@PathVariable long limit){
        Page<CrmBanner> page1 = new Page<>(page,limit);
        bannerService.page(page1,null);
        return R.ok().data("items",page1.getRecords()).data("total",page1.getTotal());
    }

    //根据id查询
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R getById(@PathVariable String id){
        bannerService.getById(id);
        return R.ok();
    }

    //添加banner
    @ApiOperation(value = "新增Banner")
    @PostMapping("addPanner")
    public R addPanner(@RequestBody CrmBanner banner){
        bannerService.save(banner);
        return R.ok();
    }


    //修改
    @ApiOperation(value = "修改Banner")
    @PostMapping("update")
    public R updateById(@RequestBody CrmBanner banner){
        bannerService.updateById(banner);
        return R.ok();
    }

    //删除
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R removeBanner(@PathVariable String id){
        bannerService.removeById(id);
        return R.ok();
    }
}

