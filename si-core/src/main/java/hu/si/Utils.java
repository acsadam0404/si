package hu.si;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.apache.commons.lang3.StringUtils;

public class Utils {
	public static String removeAccents(String text) {
		return text == null ? null : Normalizer.normalize(text, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	/**
	 * Ékezetek nélkül és whitespace mentesen adja vissza a stringet nem lesz benne zárójel lowercase lesz
	 *
	 * @param str
	 * @return
	 */
	public static String toCssClass(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		str = removeAccents(str);
		str = str.replaceAll(" ", "");
		str = str.replaceAll("\t", "");
		str = str.replaceAll("\n", "");
		str = str.replaceAll("[\\s\\-()]", "-");

		return str.toLowerCase();
	}
	
	
	public static String getShortenedString(String str, int maxSize) {
		if (StringUtils.isBlank(str) || str.length() < maxSize) {
			return str;
		}
		
		return str.substring(0, maxSize - 3) + "...";
	}

}
