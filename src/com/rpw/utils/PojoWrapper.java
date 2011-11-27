/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package com.rpw.utils;

/**
 *
 * @author radityo
 */
public class PojoWrapper {

    private Class object;
    private Object obj;

    public PojoWrapper(Object obj){
        this.obj = obj;
        this.object = obj.getClass();
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Class getObject() {
        return object;
    }

    public void setObject(Class object) {
        this.object = object;
    }

    

}
