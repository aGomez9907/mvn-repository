
package com.solvd.laba.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflection {

    private static final Logger LOGGER = LogManager.getLogger(Reflection.class);

    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("com.solvd.laba.Hospital");
            LOGGER.info("Methods of Hospital: ");
            Method[] methodList = cls.getDeclaredMethods();
            for (Method m : methodList) {
                LOGGER.info("name = " + m.getName());
                LOGGER.info("declaring class = " + m.getDeclaringClass());
                Class<?>[] parameterTypes = m.getParameterTypes();
                for (int j = 0; j < parameterTypes.length; j++)
                    LOGGER.info("parameter #" + j + " " + parameterTypes[j]);
                Class<?>[] exceptionTypes = m.getExceptionTypes();
                for (int j = 0; j < exceptionTypes.length; j++)
                    LOGGER.info("exception #" + j + " " + exceptionTypes[j]);
                LOGGER.info("return type = " + m.getReturnType());
                LOGGER.info("*******************");
            }
        } catch (Throwable e) {
            LOGGER.info(e);
        }

        try {
            Class<?> cls = Class.forName("com.solvd.laba.person.doctors.FamilyPhysician");
            LOGGER.info("Constructors of FamilyPhysician: ");
            Constructor<?>[] constructors = cls.getDeclaredConstructors();
            for (Constructor<?> ct : constructors) {
                LOGGER.info("name = " + ct.getName());
                LOGGER.info("declaring class = " + ct.getDeclaringClass());
                Class<?>[] parameterTypes = ct.getParameterTypes();
                for (int j = 0; j < parameterTypes.length; j++)
                    LOGGER.info("parameter #" + j + " " + parameterTypes[j]);
                Class<?>[] exceptionTypes = ct.getExceptionTypes();
                for (int j = 0; j < exceptionTypes.length; j++)
                    LOGGER.info("exception #" + j + " " + exceptionTypes[j]);
                LOGGER.info("*******************");
            }
        } catch (Throwable e) {
            LOGGER.info(e);
        }

        try {
            Class<?> cls = Class.forName("com.solvd.laba.rooms.PatientsRoom");
            LOGGER.info("Fields of PatientsRoom: ");
            Field[] fieldList = cls.getDeclaredFields();
            for (Field fld : fieldList) {
                LOGGER.info("name = " + fld.getName());
                LOGGER.info("declaring class = " + fld.getDeclaringClass());
                LOGGER.info("type = " + fld.getType());
                int mod = fld.getModifiers();
                LOGGER.info("modifiers = " + Modifier.toString(mod));
            }
            LOGGER.info("*******************");
        } catch (Throwable e) {
            LOGGER.error(e);
        }
    }
}

