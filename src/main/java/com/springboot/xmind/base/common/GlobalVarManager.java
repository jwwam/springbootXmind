package com.springboot.xmind.base.common;

import com.springboot.xmind.base.utils.shorter.generator.UrlShorterGeneratorSimple;

/**
 * @ClassName: GlobalVarManager
 * @Auther: zhangyingqi
 * @Date: 2019/10/10 17:32
 * @Description:
 */
public class GlobalVarManager {

    private static ThreadLocal<UrlShorterGeneratorSimple> globalVars = new ThreadLocal<>();

    public static UrlShorterGeneratorSimple getGlobalVars(){
        return globalVars.get();
    }

    public static void setGlobalVars(UrlShorterGeneratorSimple t){
        System.out.println("设置全局变量");
        globalVars.set(t);
    }

}