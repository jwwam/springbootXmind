package com.springboot.xmind.entity;

import lombok.Data;

/**
 * @ClassName: ShortUriXmindDTO
 * @Auther: zhangyingqi
 * @Date: 2019/10/12 17:11
 * @Description:
 */
@Data
public class ShortUriXmindDTO {

    /**
     * xmind ID
     */
    private String idName;
    /**
     * 原始网址
     */
    private String orignUri;

}