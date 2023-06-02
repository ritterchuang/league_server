package org.lsj.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static final JsonUtil instance = new JsonUtil();

    private ObjectMapper objectMapper; // JSON解析器

    public JsonUtil() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static JsonUtil getInstance() {
        return instance;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }



    /* JSON字串轉物件相關 */
    // JSON字串轉物件
    public <T> T readValue(String string, Class<T> type) throws JsonProcessingException {
        return this.objectMapper.readValue(string, type);
    }

    // JSON字串轉物件
    public <T> T readValue(String string, TypeReference<T> valueTypeRef) throws JsonProcessingException {
        return this.objectMapper.readValue(string, valueTypeRef);
    }

    // JSON字串轉物件(不拋錯)
    public <T> T readValueWithoutException(String string, Class<T> type) {
        try {
            return this.objectMapper.readValue(string, type);
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }

    // JSON字串轉物件(不拋錯) MAP用
    public <T> T readValueWithoutException(String string, TypeReference<T> valueTypeRef) {
        try {
            return this.objectMapper.readValue(string, valueTypeRef);
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }



    /* JSON字串轉JsonNode相關 */
    // JSON字串轉JsonNode
    public JsonNode readTree(String string) throws JsonProcessingException {
        return this.objectMapper.readTree(string);
    }

    // JSON字串轉JsonNode(不拋錯)
    public JsonNode readTreeWithoutException(String string){
        try {
            return this.objectMapper.readTree(string);
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }



    /* 轉JSON字串相關 */
    // 物件轉JSON字串
    public String writeValueAsString(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

    // 物件轉JSON字串(不拋錯)
    public String writeValueAsStringWithoutException(Object object) {
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }



    /* 深複製相關 */
    // 深複製
    public <T> T deepCopy(T object, Class<T> type) {
        try {
            return this.readValueWithoutException(this.writeValueAsStringWithoutException(object), type);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    // 深複製陣列
    public <T> List<T> deepCopyList(List<T> objectList, Class<T> type) {
        List<T> result = new ArrayList<T>();

        for (int objectIndex = 0; objectIndex < objectList.size(); objectIndex++) {
            result.add(this.deepCopy(objectList.get(objectIndex), type));
        }

        return result;
    }
}
