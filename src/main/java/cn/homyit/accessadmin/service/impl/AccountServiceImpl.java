package cn.homyit.accessadmin.service.impl;

import cn.homyit.accessadmin.dto.LoginDTO;
import cn.homyit.accessadmin.entity.Account;
import cn.homyit.accessadmin.mapper.AccountMapper;
import cn.homyit.accessadmin.service.AccountService;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author Ziqiang CAO
 * @since 2021-07-28
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public LoginDTO login(String username, String password) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPath("redirect:/");

        Account account = lambdaQuery().eq(Account::getUsername, username).one();
        if (account == null) {
            loginDTO.setError("用户名不存在");
            return loginDTO;
        }

        MD5 md5 = new MD5(account.getSalt().getBytes());
        String digestHex = md5.digestHex(password);
        if (!digestHex.equals(account.getPassword())) {
            loginDTO.setError("密码错误");
            return loginDTO;
        }

        loginDTO.setPath("login/main");
        loginDTO.setAccount(account);
        return loginDTO;
    }

    @Override
    public IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper) {
        return baseMapper.accountPage(page, wrapper);
    }

    /**
     * 根据accountId查询账号信息
     *
     * @param id
     * @return
     */
    @Override
    public Account getAccountById(Long id) {
        return baseMapper.selectAccountById(id);
    }
}
