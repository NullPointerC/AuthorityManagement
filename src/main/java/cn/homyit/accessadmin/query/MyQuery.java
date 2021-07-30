package cn.homyit.accessadmin.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author Ziqiang CAO
 * @email 1213409187@qq.com
 */
@Data
public class MyQuery<T> {

    /**
     * 分页参数
     */
    private Page<T> page;

    /**
     * 条件构造器
     */
    private QueryWrapper<T> wrapper;
}
