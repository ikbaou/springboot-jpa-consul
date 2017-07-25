package com.example.demo.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ClassUtils {
	
	/**
	 * get the actual type of a class for an index (recursively search through type of types)
	 * 
	 * @param index
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getType(final int index, final Class<?> clazz) {
		
		if(clazz == null){
			//LOG.warn("Could not get Type for Class: {} and index: {}", clazz, index);
			return null;
		}else{		
			if( clazz.getGenericSuperclass() instanceof ParameterizedType ){			
				final Type type = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[index];
				if( type instanceof Class ){
					return (Class<T>) ((ParameterizedType)type).getActualTypeArguments()[index];
				}else if( type instanceof TypeVariable ){
					return (Class<T>)((TypeVariable<?>)type).getBounds()[0];
				}else{						
					return (Class<T>)type;
				}
			}else{
				return getType(index, clazz.getSuperclass());
			}
		}
	}

	/**
	 * perform A.setProperty( B.getProperty ) IF B.getProperty is NOT null
	 * 
	 * also known as ...
	 * 
	 * if(B.getProperty!=null){A.setProperty(B.getProperty);}
	 * 
	 * 
	 * @param getter
	 * @param setter
	 */
	public static <T> void setIfNotNull(final Consumer<T> setter, final Supplier<T> getter) {
		final T t = getter.get();
		if (null != t) {
			setter.accept(t);
		}
	}

	/**
	 * perform A.setProperty( B.getProperty ) IF B.getProperty is NOT null or an
	 * Empty String
	 * 
	 * also known as ...
	 * 
	 * if(StrngUtil.isEmptyOrNull(B.getProperty!=null)){A.setProperty(B.getProperty);}
	 * 
	 * 
	 * @param getter
	 * @param setter
	 */
	public static <T> void setIfNotEmpty(final Consumer<String> setter, final Supplier<String> getter) {
		final String str = getter.get();
		if (str != null && !"".equals(str.trim())) {
			setter.accept(getter.get());
		}
	}
}
