package com.syh.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 沈煜辉
 * @date 2019/9/28 23:25
 * @describe 日期转换器
 */
public class DateConverter implements Converter<String,Date> {

    @Nullable
    @Override
    public Date convert(String s) {
        if(!StringUtils.isEmpty(s)){
            SimpleDateFormat dateFormat = null;
            if(s.trim().length()==10){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
            if(s.trim().trim().length()==19){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            if(s.trim().trim().length()==16){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            }
            Date date = null;
            try {
                date = dateFormat.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
        return null;
    }
}
