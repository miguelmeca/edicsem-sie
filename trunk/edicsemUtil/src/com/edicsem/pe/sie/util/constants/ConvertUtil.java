package com.edicsem.pe.sie.util.constants;

import java.beans.PropertyDescriptor;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class ConvertUtil {

    private static Log log = LogFactory.getLog(ConvertUtil.class);

    /**
     * Convenience method to convert a form to a POJO and back again
     * 
     * @param o
     *            the object to tranfer properties from
     * @return converted object
     */
    public static Object convert(Object o) throws Exception {
        if (o == null) {
            return null;
        }
        Object target = o;
        BeanUtils.copyProperties(target, o);
        return target;
    }

    /**
     * Convenience method to convert an object into other object
     * copying the properties from the source object to the new
     * object 
     * 
     * @param o
     *            the object to tranfer properties from
     * @param clazz
     *            the type class of the target new object
     * @return converted object
     */
    public static Object convert(Object o, Class clazz) throws Exception {
        if (o == null) {
            return null;
        }
        Object target = clazz.newInstance();
        BeanUtils.copyProperties(target, o);
        return target;
    }

    /**
     * Convenience method to convert Lists (in a Form) from POJOs to Forms. Also
     * checks for and formats dates.
     * 
     * @param o
     * @return
     * @throws Exception
     */
    public static Object convertLists(Object o) throws Exception {
        if (o == null) {
            return null;
        }

        Object target = null;

        PropertyDescriptor[] origDescriptors = PropertyUtils
                .getPropertyDescriptors(o);

        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();

            if (origDescriptors[i].getPropertyType().equals(List.class)) {
                List list = (List) PropertyUtils.getProperty(o, name);
                for (int j = 0; j < list.size(); j++) {
                    Object origin = list.get(j);
                    target = convert(origin);
                    list.set(j, target);
                }
                PropertyUtils.setProperty(o, name, list);
            }
        }
        return o;
    }
}
