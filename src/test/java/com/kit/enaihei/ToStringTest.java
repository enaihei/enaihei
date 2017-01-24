/*
 * 修订记录：
 * aiping.yuan 10:06 创建
 */
package com.kit.enaihei;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kit.enaihei.commons.ToString;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 脱敏测试
 * <p>Created by aiping.yuan on 2017/1/24.<p>
 * @see com.kit.enaihei.commons.ToString
 */
public class ToStringTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testObject() {
        Entity entity = new Entity();
        entity.setId(1);
        entity.setName("张三");
        entity.setAverage(4f);
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", "张三");
        map.put("mobile", "15112355647");
        entity.setMap(map);
        Entity _entity = new Entity();
        _entity.setId(2);
        _entity.setName("李四");
        _entity.setAverage(5f);
        Map<String, Object> _map = Maps.newHashMap();
        _map.put("name", "李四");
        _map.put("mobile", "15234568795");
        _entity.setMap(_map);
        entity.setEntities(Lists.newArrayList(_entity));

        logger.info("==================>{}", entity);
        logger.info("==================>{}", entity.getMap());

    }
















    public static class Entity implements Serializable {
        private static final long serialVersionUID = 2208721911622761401L;
        private int    id;
        private String name;
        private float  average;
        private Map<String, Object> map;
        private List<Entity> entities;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getAverage() {
            return average;
        }

        public void setAverage(float average) {
            this.average = average;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        public void setMap(Map<String, Object> map) {
            this.map = map;
        }

        public List<Entity> getEntities() {
            return entities;
        }

        public void setEntities(List<Entity> entities) {
            this.entities = entities;
        }

        @Override
        public String toString() {
            return ToString.toString(this);
        }
    }
}
