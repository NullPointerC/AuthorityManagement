package cn.homyit.accessadmin.mp;

import cn.homyit.accessadmin.entity.Account;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;

/**
 * 自动填充类
 * @author Ziqiang CAO
 * @email 1213409187@qq.com
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")) {
            this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        }

        if (metaObject.hasSetter("createAccountId")) {
            Object account = RequestContextHolder.getRequestAttributes().getAttribute("account", RequestAttributes.SCOPE_SESSION);

            if (account != null) {
                Long accountId = ((Account) account).getAccountId();
                this.strictInsertFill(metaObject, "createAccountId", Long.class, accountId);
            }
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("modifiedTime")) {
            this.strictUpdateFill(metaObject, "modifiedTime", LocalDateTime.class, LocalDateTime.now());
        }

        if (metaObject.hasSetter("modifiedAccountId")) {
            Object account = RequestContextHolder.getRequestAttributes().getAttribute("account", RequestAttributes.SCOPE_SESSION);

            if (account != null) {
                Long accountId = ((Account) account).getAccountId();
                this.strictUpdateFill(metaObject, "modifiedAccountId", Long.class, accountId);
            }
        }
    }
}
