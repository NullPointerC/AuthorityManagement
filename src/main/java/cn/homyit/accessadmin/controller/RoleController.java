package cn.homyit.accessadmin.controller;


import cn.homyit.accessadmin.entity.Account;
import cn.homyit.accessadmin.entity.Role;
import cn.homyit.accessadmin.service.AccountService;
import cn.homyit.accessadmin.service.ResourceService;
import cn.homyit.accessadmin.service.RoleService;
import cn.homyit.accessadmin.util.ResultUtil;
import cn.homyit.accessadmin.vo.TreeVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Ziqiang CAO
 * @since 2021-07-28
 */
@Controller
@RequestMapping("/role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AccountService accountService;

    /**
     * 进入角色列表页
     *
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "role/roleList";
    }

    /**
     * 查询角色列表
     *
     * @param roleName
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(String roleName, Long page, Long limit) {
        QueryWrapper<Role> queryWrapper = Wrappers.<Role>query()
                .like(StringUtils.isNotBlank(roleName), "role_name", roleName)
                .orderByDesc("role_id");
        Page<Role> resultPage = roleService.page(new Page<>(page, limit), queryWrapper);
        return ResultUtil.buildPageR(resultPage);
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd() {
        return "role/roleAdd";
    }

    /**
     * 新增操作
     *
     * @param role
     * @return
     */
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Role role) {

        return ResultUtil.buildR(roleService.saveRole(role));
    }

    /**
     * 进入页面修改
     *
     * @return
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "role/roleUpdate";
    }

    /**
     * 修改操作
     *
     * @param role
     * @return
     */
    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Role role) {
        return ResultUtil.buildR(roleService.updateRole(role));
    }

    /**
     * 进入角色详情页
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "/role/roleDetail";
    }

    /**
     * 删除操作
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id) {
        Integer count = accountService.lambdaQuery().eq(Account::getRoleId, id).count();
        if (count > 0) {
            return R.failed("有账户正拥有该角色,不允许删除");
        }
        return ResultUtil.buildR(roleService.removeById(id));
    }

    /**
     * 查询角色所拥有的资源
     *
     * @param roleId
     * @return
     */
    @GetMapping({"listResource", "listResource/{roleId}", "listResource/{roleId}/{flag}"})
    @ResponseBody
    public R<List<TreeVO>> listResource(@PathVariable(required = false) Long roleId,
                                        @PathVariable(required = false) Integer flag) {
        log.info(roleId + ":" + flag);
        return R.ok(resourceService.listResource(roleId, flag));
    }

}

