package com.fvilla.api.dateHandler;

import java.lang.annotation.Annotation;
import java.util.Date;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;


public class DateParameterConverterProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, java.lang.reflect.Type genericType,
			Annotation[] annotations) {
		 if (Date.class.equals(rawType)) {
	            @SuppressWarnings("unchecked")
	            ParamConverter<T> paramConverter = (ParamConverter<T>) new DateParameterConverter();
	            return paramConverter;
	        }
	        return null;
	}

}