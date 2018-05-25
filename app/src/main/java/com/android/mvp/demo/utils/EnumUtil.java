package com.android.mvp.demo.utils;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class EnumUtil {
    public enum ActivityType{
        HAVE_TITLE(0),
        NO_TITLE(1);
        int type;

        ActivityType(int type) {
            this.type = type;
        }

        public ActivityType getType(int type){
            switch (type) {
                case 0:
                    return HAVE_TITLE;
                case 1:
                    return NO_TITLE;
                default:
                    return HAVE_TITLE;
            }
        }

    }

    public enum allLanguage{
        Chinese,
        English,
        Russian,
        German,
        French,
        Spanish,
        Portuguese,
        Arabic,
        SystemDefault,
    }
}
