package opg.softuni.fdmc.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public final class GenericComparator {

    private GenericComparator(){

    }

    public static <T> List<T> compare(List<T> l, String field, Class<T> c)
            throws ReflectiveOperationException, IntrospectionException {

        final Field f = c.getDeclaredField(field);

        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(),c);
        final Method getter = propertyDescriptor.getReadMethod();
        Class<?> returnType = getter.getReturnType();

        if (Comparable.class.isAssignableFrom(returnType) || returnType.isPrimitive()) {
            // The result from invoke() will be Comparable
            l.sort((e1, e2) -> {
                try {
                    Comparable val1 = (Comparable) getter.invoke(e1);
                    Comparable val2 = (Comparable) getter.invoke(e2);
                    return val1.compareTo(val2);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            throw new IllegalArgumentException("Cannot compare on field " + field + " of type " + returnType.getName());
        }
        return l;
    }
}
