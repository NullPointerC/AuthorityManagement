package cn.homyit.accessadmin.controller;


import cn.homyit.accessadmin.entity.Customer;
import cn.homyit.accessadmin.query.MyQuery;
import cn.homyit.accessadmin.service.CustomerService;
import cn.homyit.accessadmin.util.QueryUtil;
import cn.homyit.accessadmin.util.ResultUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author Ziqiang CAO
 * @since 2021-07-28
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 进入列表页
     *
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "customer/customerList";
    }

    /**
     * 查询方法
     *
     * @param param
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(@RequestParam Map<String, String> param) {
        MyQuery<Customer> myQuery = QueryUtil.<Customer>buildMyQuery(param);
        //LambdaQueryWrapper<Customer> wrapper = Wrappers.<Customer>lambdaQuery()
        //        .like(StringUtils.isNotBlank(realName), Customer::getRealName, realName)
        //        .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
        //        .orderByDesc(Customer::getCustomerId);
        Page<Customer> customerPage = customerService.page(myQuery.getPage(), myQuery.getWrapper().orderByDesc("customer_id"));
        return ResultUtil.buildPageR(customerPage);
    }

    /**
     * 进入新增页
     *
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd() {
        return "customer/customerAdd";
    }

    /**
     * 新增客户
     *
     * @param customer
     * @return
     */
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Customer customer) {
        return ResultUtil.buildR(customerService.save(customer));
    }

    /**
     * 进入修改页
     *
     * @return
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable("id") Long id, Model model) {

        Customer customer = customerService.getById(id);
        model.addAttribute(customer);
        return "customer/customerUpdate";
    }

    /**
     * 修改客户
     *
     * @param customer
     * @return
     */
    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Customer customer) {
        return ResultUtil.buildR(customerService.updateById(customer));
    }


    /**
     * 删除客户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable("id") Long id) {
        return ResultUtil.buildR(customerService.removeById(id));
    }


    /**
     * 客户详情
     *
     * @param id
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "/customer/customerDetail";
    }
}
