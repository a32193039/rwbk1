package top.lrshuai.plus.springbootmybatisplus.rwbk.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2024-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CnRwbk implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 关键词
     */
    private String content;

    /**
     * 描述
     */
    private String description;

    /**
     * 人物名称
     */
    private String name;

    /**
     * 人物职位
     */
    private String zhiwei;

    /**
     * 人物简介
     */
    private String rwjj;

    /**
     * 外文名称
     */
    private String wwname;

    /**
     * 毕业院校
     */
    private String byyx;

    /**
     * 目录
     */
    private String ml;

    private String content1;

    private String content2;

    private String content3;

    private String content4;

    private String content5;

    private String content6;

    /**
     * 大事年表
     */
    private String dsnb;

    /**
     * 词条标签
     */
    private String ctbq;

    /**
     * 头像url
     */
    private String txurl;

    private String isflag;


}
