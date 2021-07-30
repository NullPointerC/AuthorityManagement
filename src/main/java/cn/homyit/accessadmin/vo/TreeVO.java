package cn.homyit.accessadmin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Ziqiang CAO
 * @email 1213409187@qq.com
 */
@Data
public class TreeVO {

    private String title;

    private Long id;

    private List<TreeVO> children;

    private boolean checked;
}
