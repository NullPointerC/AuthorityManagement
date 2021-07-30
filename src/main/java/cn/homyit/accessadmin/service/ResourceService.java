package cn.homyit.accessadmin.service;

import cn.homyit.accessadmin.entity.Resource;
import cn.homyit.accessadmin.vo.ResourceVO;
import cn.homyit.accessadmin.vo.TreeVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author Ziqiang CAO
 * @since 2021-07-28
 */
public interface ResourceService extends IService<Resource> {
    /**
     * 根据用户的roleId查询所拥有的资源
     *
     * @param roleId
     * @return
     */
    List<ResourceVO> listResourceByRoleId(Long roleId);

    /**
     * 查询系统资源供前端组件渲染
     *
     * @return
     */
    List<TreeVO> listResource(Long roleId, Integer flag);

    /**
     * 将资源转换为代码模块名称的集合
     *
     * @param resourceVOS
     * @return
     */
    HashSet<String> convert(List<ResourceVO> resourceVOS);
}
