package com.hack23.cia.systemintegrationtest;

import java.util.Map;
import java.util.Map.Entry;

import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.helpers.ClassHelper;
import org.dussan.vaadin.dcharts.helpers.JsonHelper;
import org.dussan.vaadin.dcharts.helpers.ObjectHelper;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.junit.Assert;
import org.junit.Test;

public class ChartTest extends Assert {

	@Test
	public void addSerieTest() {
		final XYseries label = new XYseriesFix();
		label.setLabel("sune");
		toJsonString(label);
		System.out.println(label);
		assertNotNull("Fucked",label);
	}

	static class XYseriesFix extends XYseries {

		@Override
		public String getValue() {
			return toJsonString(this);
		}

	}

	static class XYaxisFix extends XYaxis {

		public XYaxisFix() {
			super();
		}
		public XYaxisFix(XYaxes y) {
			super(y);
		}

		@Override
		public String getValue() {
			return toJsonString(this);
		}

	}


	public static String toJsonString(Object object) {
		try {
			Map<String, Object> values = ClassHelper.getFieldValues(object);
			StringBuilder builder = new StringBuilder();
			for (Entry<String, Object> entry : values.entrySet()) {
				String fieldName = entry.getKey();
				Object fieldValue = entry.getValue();

				if (!fieldName.contains("jacocoData")) {
				if (ObjectHelper.isArray(fieldValue)) {

					if (fieldValue instanceof Object[][]) {
						fieldValue = ObjectHelper
								.toArrayString((Object[][]) fieldValue);
					} else if (fieldValue instanceof boolean[]) {

						System.out.println(fieldName);
						System.out.println(fieldValue.getClass().getName());
					} else

					{
						fieldValue = ObjectHelper
								.toArrayString((Object[]) fieldValue);
					}
				}

				if (fieldValue != null) {
					fieldValue = !ObjectHelper.isString(fieldValue) ? fieldValue
							: fieldValue.toString().replaceAll("\"", "'");
					builder.append(builder.length() > 0 ? ", " : "");
					builder.append(fieldName + ": ");
					builder.append(ObjectHelper.isString(fieldValue) ? "\""
							: "");
					builder.append(fieldValue);
					builder.append(ObjectHelper.isString(fieldValue) ? "\""
							: "");
				}
				}
			}
			return builder.insert(0, "{").append("}").toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
