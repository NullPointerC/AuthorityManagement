package cn.homyit.accessadmin.service;

import cn.homyit.accessadmin.dto.LoginDTO;
import cn.homyit.accessadmin.entity.Account;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author Ziqiang CAO
 * @since 2021-07-28
 */
public interface AccountService extends IService<Account> {

    LoginDTO login(String username, String password);

    IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper);
    /**
     * 根据accountId查询账号信息
     * @param id
     * @return
     */
    Account getAccountById(Long id);
}
