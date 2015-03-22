/*******************************************************************************
 * Copyright 2013 Thomas Letsch (contact@thomas-letsch.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package hu.si.vaadin.converter;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class DateToCalendarConverter implements Converter<Date, Calendar> {

    private static final long serialVersionUID = 1L;

    @Override
    public Calendar convertToModel(Date value, Class<? extends Calendar> targetType, Locale locale) throws Converter.ConversionException {
        if (value == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(value);
        return calendar;
    }

    @Override
    public Date convertToPresentation(Calendar value, Class<? extends Date> targetType, Locale locale) throws Converter.ConversionException {
        if (value == null) {
            return null;
        }
        return value.getTime();
    }

    @Override
    public Class<Calendar> getModelType() {
        return Calendar.class;
    }

    @Override
    public Class<Date> getPresentationType() {
        return Date.class;
    }
    

	private static final DateToCalendarConverter instance = new DateToCalendarConverter();

	private DateToCalendarConverter() {
		/* singleton */
	}

	public static DateToCalendarConverter getInstance() {
		return instance;
	}

}
