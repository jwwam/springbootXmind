package com.springboot.xmind.base.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName: UUIDUtils
 * @Auther: zhangyingqi
 * @Date: 2018/9/3 11:03
 * @Description:
 */
@Component
public class UUIDUtils {

    public static String getUuid(){
        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        return uuid;
    }

}
