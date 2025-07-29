package com.showcase.application.springbootbackend.utils;

import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Utilities {

    public static Logger getLogger(Class<?> aClass) {
        return LoggerFactory.getLogger(aClass);
    }

    public static <T> Sort sortGenerator(Class<T> entityType, Sort defaultSort) {
        Sort sort = defaultSort;

        if (sort == null) {
            String idName = getIdName(entityType);
            if (idName != null && !idName.isEmpty()) {
                sort = Sort.by(Sort.Direction.ASC, idName);
            }
        }
        return sort;
    }

    public static <T> String getIdName(Class<T> entityType) {
        if (entityType == null) {
            return null;
        }

        Field idField = getIdField(entityType);
        return idField != null ? idField.getName() : null;
    }

    public static <T> Field getIdField(Class<T> entityType) {
        Class<? super T> superclass = entityType.getSuperclass();
        if (superclass == null) {
            return null;
        }

        for (Field field : superclass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        for (Field field : entityType.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        return null;
    }

    public static <T> T setFieldValue(T entity, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field;
        try {
            field = entity.getClass().getSuperclass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException ignored) {
            try {
                field = entity.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                throw e;
            }
        }
        field.setAccessible(true);
        field.set(entity, value);
        return entity;
    }

    public static String capitalizeEachWord(String input) {

        if(input == null)
            return "";
        return Arrays.stream(input.split("\\s+"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
