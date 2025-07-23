package com.hy.user.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.user.vo.MemberVo;
import com.hy.user.entity.Member;
import com.hy.entity.WebResponse;
import com.hy.user.service.MemberService;
import com.hy.permission.Permission;
import com.hy.i18n.I18nUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;


@Api(tags = "控制器")
@RestController
@RequestMapping("/api/user/member")
public class MemberController {

    private  final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    /**
     * 查询所有
     */
    @ApiOperation("查询所有")
    @Permission(path = "/user/member")
    @PostMapping("/list")
    public WebResponse<List<Member>> list(@RequestBody MemberVo entity) {
         Page<Member> page = new Page<>(entity.getCurrent(), entity.getPageSize());
         LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery(Member.class);
         Page<Member> MemberPage = memberService.page(page, wrapper);
         return WebResponse.Page(MemberPage.getRecords(), MemberPage.getTotal());
    }

    /**
     * 新增
     */
    @ApiOperation("新增")
    @Permission(path = "/user/member", type = Permission.Type.Write)
    @PostMapping("/add")
    public WebResponse<Boolean> add(@RequestBody Member entity) {
         Boolean save = memberService.save(entity);
          return WebResponse.OK(I18nUtils.getMessage(save ? "add.success" : "add.fail"),save);
    }
    /**
    * 修改
    */
    @ApiOperation("修改")
    @Permission(path = "/user/member", type = Permission.Type.Write)
    @PostMapping("/update")
    public WebResponse<Boolean> update(@RequestBody Member entity) {
        Boolean update = memberService.updateById(entity);
        return WebResponse.OK(I18nUtils.getMessage(update ? "update.success" : "update.fail"),update);
        }
    /**
     * 删除
     */
    @ApiOperation("删除")
    @Permission(path = "/user/member", type = Permission.Type.Write)
    @GetMapping("/delete")
    public WebResponse<Boolean> delete(@RequestParam String id) {
        return WebResponse.OK(memberService.removeById(id));
    }
            /**
             * 查询
             */
            @ApiOperation("查询")
            @Permission(path = "/user/member")
            @GetMapping("/info")
            public WebResponse<Member> info(@RequestParam String id) {
                return WebResponse.OK(memberService.getById(id));
            }

}
