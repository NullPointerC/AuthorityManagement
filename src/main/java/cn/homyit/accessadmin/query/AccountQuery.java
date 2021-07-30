package cn.homyit.accessadmin.query;

import lombok.Data;

/**
 * @author Ziqiang CAO
 * @email 1213409187@qq.com
 */
@Data
public class AccountQuery {
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 时间范围
     */
    private String createTimeRange;

    /**
     * 当前页数
     */
    private Long page;

    /**
     * 每页条数
     */
    private Long limit;
}
