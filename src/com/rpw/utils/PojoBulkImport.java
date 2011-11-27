/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */
package com.rpw.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author radityo
 */
public class PojoBulkImport extends PojoWrapper {

    private Set key;
    private Map values;
    private String prefix;

    public PojoBulkImport(Object o, Map values) {
        super(o);

        this.key = values.keySet();
        this.values = values;
        this.prefix = getClass().getName().toLowerCase() + "_";
    }

    public PojoBulkImport(Object o, Map values, String prefix) {
        super(o);

        this.key = values.keySet();
        this.values = values;
        this.prefix = prefix;
    }

    public void process() {



        Iterator keyIterator = key.iterator();
        while (keyIterator.hasNext()) {
            String next = (String) keyIterator.next();

            if (next.startsWith(prefix)) {
                String[] nextSplited = next.split(prefix);
                String[] value = (String[]) values.get(next);
                try {

                    Field declaredField = getObject().getDeclaredField(nextSplited[1]);
                    declaredField.setAccessible(true);
                    Class<?> type = declaredField.getType();
                    Constructor<?> constructor;
                    Object cast = null;
                    try {
                        constructor = type.getConstructor(String.class);
                        cast = constructor.newInstance(value[0]);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(PojoBulkImport.class.getName()).log(Level.SEVERE, null, ex);
                        cast = typeProcess(type.getName(), value[0]);
                    }

                    declaredField.set(getObj(), cast);
                } catch (InstantiationException ex) {
                    Logger.getLogger(PojoBulkImport.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(PojoBulkImport.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(PojoBulkImport.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(PojoBulkImport.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchFieldException ex) {
                    Logger.getLogger(PojoBulkImport.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(PojoBulkImport.class.getName()).log(Level.SEVERE, null, ex);
                }




            }

        }

    }

    private Object typeProcess(String typeName, String value) {
        Object x = null;

        if ("java.lang.String".equals(typeName)) {
            x = value;
        } else if ("java.lang.Integer".equals(typeName)) {
            x = Integer.valueOf(value);
        } else if ("java.lang.Boolean".equals(typeName)) {
            x = Boolean.valueOf(value);
        } else if ("java.lang.Double".equals(typeName)) {
            x = Double.valueOf(value);
        } else if ("java.lang.Byte".equals(typeName)) {
            x = Byte.valueOf(value);
        } else if ("java.lang.Float".equals(typeName)) {
            x = Float.valueOf(value);
        } else if ("java.lang.Short".equals(typeName)) {
            x = Short.valueOf(value);
        } else if ("java.lang.Long".equals(typeName)) {
            x = Long.valueOf(value);
        }
        return x;
    }
}
