/*
 * 修订记录：
 * aiping.yuan 9:57 创建
 */
package com.kit.enaihei.commons;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>Created by aiping.yuan on 2017/1/24.<p>
 */
public class ToString {

    private static final String[] MARK_VALUE_LIST = new String[] {"name", "mobile"};

    public static String toString(Object obj) {
        return toString(obj, null);
    }

    public static String toString(Object obj, String... markValue) {
        return markToString(obj, (null == markValue ? MARK_VALUE_LIST : markValue));
    }

    public static String toStringJSON(String json) {
        return toStringJSON(json, null);
    }

    public static String toStringJSON(String json, String... markValue) {
        try {
            return markToString(JSON.parseObject(json, new TypeReference<Map<String, Object>>(){})
                    , (null == markValue ? MARK_VALUE_LIST : markValue));
        } catch (Exception e) {
            //- skiping 不是json，返回原数据
        }
        return json;
    }

    private static String markToString(Object obj, String... markValue) {
        SerializeWriter out = new SerializeWriter(
                SerializerFeature.WriteMapNullValue
                , SerializerFeature.WriteNullListAsEmpty
        );
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.getValueFilters().add(initFilter(markValue));
        serializer.write(obj);
        return out.toString();
    }


    private static ValueFilter initFilter(final String... markValue) {
        return new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                if (null != value && !Strings.isNullOrEmpty(value.toString())) {
                    List<String> markValues = Arrays.asList(markValue);
                    if (markValues.contains(name)) {
                        return MarkToString.mask(value.toString());
                    }
                }
                return value;
            }
        };
    }
}
