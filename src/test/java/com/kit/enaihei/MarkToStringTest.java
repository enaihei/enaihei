/*
 * 修订记录：
 * aiping.yuan 17:54 创建
 */
package com.kit.enaihei;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.kit.enaihei.commons.MarkToString;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

/**
 * 脱敏测试
 * <p>Created by aiping.yuan on 2017/1/23.<p>
 * @see com.kit.enaihei.commons.MarkToString
 */
public class MarkToStringTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testObject() {
        A<C> a = new A();
        a.setA("aaaaaaaaaaa");
        a.setB("bbbbbbbbbbb");
        C c = new C();
        c.setD("ddddddddddddd");
        c.setE("eeeeeeeeeeeee");
        a.setC(c);
        logger.info("=======================>{}", a);
    }

    @Test
    public void testJson() {
        String json = "{\"idCard\":\"522227199001291889\",\"name\":\"张三\",\"mobile\":\"13883482726\"," +
        "\"contactList\":[{\"mobile\":\"13883482726\",\"name\":\"李四\"}]}";
        logger.info("======================>{}", MarkToString.markToJSON(json));
    }

    @Test
    public void testJsonToObject() {
        String json = "{\"idCard\":\"522227199001291889\",\"name\":\"张三\",\"mobile\":\"13883482726\"," +
                "\"contactList\":[{\"mobile\":\"13883482726\",\"name\":\"李四\"}]}";
        D d = new D();
        d.setMap(JSON.parseObject(json, new TypeReference<Map<String, String>>(){}));
        logger.info("======================>{}", MarkToString.toString(d));
    }



    class A<T> implements Serializable {
        private static final long serialVersionUID = 1612279976323528282L;

        @MarkToString.Mark
        private String a;

        @MarkToString.Hide
        private String b;

        @MarkToString.Mark
        private T c;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public T getC() {
            return c;
        }

        public void setC(T c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return MarkToString.toString(this);
        }
    }

    class C implements Serializable {
        private static final long serialVersionUID = -6054007018565738566L;

        private String d;
        @MarkToString.Mark
        private String e;

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }

        @Override
        public String toString() {
            return MarkToString.toString(this);
        }
    }

    class D {
        @MarkToString.Mark(maskKeys = {"idCard", "name", "mobile", "contactList"})
        Map<String, String> map;

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
    }
}
