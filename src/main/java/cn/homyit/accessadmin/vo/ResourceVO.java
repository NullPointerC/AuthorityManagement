package cn.homyit.accessadmin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Ziqiang CAO
 * @email 1213409187@qq.com
 */
@Data
public class ResourceVO {
    /**
     * 主键
     */
    private Long resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 下级资源
     */
    private List<ResourceVO> subs;
}
