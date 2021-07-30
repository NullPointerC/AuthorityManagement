package cn.homyit.accessadmin.mapper;

import cn.homyit.accessadmin.entity.Resource;
import cn.homyit.accessadmin.vo.ResourceVO;
import cn.homyit.accessadmin.vo.TreeVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author Ziqiang CAO
 * @since 2021-07-28
 */
public interface ResourceMapper extends BaseMapper<Resource> {


    /**
     * 查询当前登录人的资源
     *
     * @param wrapper
     * @return
     */
    List<ResourceVO> listResource(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper);

    List<TreeVO> listResourceByRoleId(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper,
                                      @Param("roleId") Long roleId);
}
