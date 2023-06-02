package org.lsj.test.utils;

import org.junit.platform.commons.support.HierarchyTraversalMode;
import org.junit.platform.commons.support.ReflectionSupport;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

public class ReflectionUtil {

    public static void setInstanceStaticFinalField(Class<?> clazz, String fieldName, Object replaceField) throws NoSuchFieldException, IllegalAccessException {
        final Field field = changeFieldAccessibleAndModifier(clazz, fieldName);

        final Object originalField = field.get(replaceField);
        field.set(originalField, replaceField);
    }

    public static Object getInstanceStaticFinalField(Class<?> clazz, String fieldName, Object replaceField) throws NoSuchFieldException, IllegalAccessException {
        final Field field = changeFieldAccessibleAndModifier(clazz, fieldName);

        return field.get(replaceField);
    }

    // 反射目標物件的成員
    public static void setMockFinalFieldToTarget(Object target, String fieldName, Object replaceField) throws NoSuchFieldException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, replaceField);
    }

    // 反射目標物件父類別的成員
    public static void setMockSuperClassFinalFieldToTarget(Class<?> superClazz, Object target, String fieldName, Object replaceField) throws NoSuchFieldException, IllegalAccessException {
        Field field = superClazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, replaceField);
    }

    private static Field changeFieldAccessibleAndModifier(Class<?> clazz, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        final List<Field> fieldList = ReflectionSupport.findFields(clazz, field -> field.getName().equals(fieldName), HierarchyTraversalMode.TOP_DOWN);
        final Field field = fieldList.get(0);  // field name 照理說只會有一個

        field.setAccessible(true);

        // 修改裝飾子, 避免 final 無法修改
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        return field;
    }

}
