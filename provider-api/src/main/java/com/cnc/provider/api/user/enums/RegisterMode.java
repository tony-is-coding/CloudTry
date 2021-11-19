package com.cnc.provider.api.user.enums;

public enum RegisterMode {
    MOBILE(1, "手机"),
    WECHAT(2, "微信"),
    QQ(3, "QQ"),
    ALIPAY(4, "支付宝");

    private final Integer code;
    private final String desc;

    RegisterMode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }

    public static RegisterMode valueOf(int value) {
        for (RegisterMode e : RegisterMode.values()) {
            if (e.getCode() == value) {
                return e;
            }
        }
        return MOBILE;
    }

    @Override
    public String toString() {
        return getDesc();
    }
}
