package com.springboot.xmind.base.utils.shorter;

import com.springboot.xmind.base.utils.shorter.generator.StringGeneratorRandom;
import com.springboot.xmind.base.utils.shorter.generator.UrlShorterGeneratorSimple;
import com.springboot.xmind.base.utils.shorter.shorter.ShorterString;
import com.springboot.xmind.base.utils.shorter.storage.ShorterStorageMemory;

/**
 * @ClassName: main
 * @Auther: zhangyingqi
 * @Date: 2019/10/10 16:33
 * @Description:
 */
public class main {

    public static void main(String[] args) {
            UrlShorterGeneratorSimple simple = new UrlShorterGeneratorSimple();
            simple.setGenerator(new StringGeneratorRandom(5));
            simple.setShorterStorage(new ShorterStorageMemory<ShorterString>());
            String shorter = simple.generate("www.baidu.com").getShorter();
            //assert shorter.length()==5;
            System.out.println(shorter);
            System.out.println(getUri(shorter));
    }

    public static String getUri(String shorter){
        UrlShorterGeneratorSimple simple = new UrlShorterGeneratorSimple();
        return simple.getShorterStorage().get(shorter);
    }

}