package com.springboot.xmind.base.utils.shorter.shorter;


import com.springboot.xmind.base.utils.shorter.ShorterGetter;

/**
 * 返回短码和密码
 * Created by luoguo on 2017/3/24.
 */
public class ShorterString implements ShorterGetter<String> {
    private String shorter;

    public ShorterString() {
    }

    public ShorterString(String shorter) {
        setShorter(shorter);
    }

    public String getShorter() {
        return shorter;
    }

    public void setShorter(String shorter) {
        this.shorter = shorter;
    }


}
