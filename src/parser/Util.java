package parser;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.DatatypeConverter;

public class Util {
	
	protected static Charset charset = StandardCharsets.UTF_8;
	
	public static String asHtmlHex(String str){
		return "%".concat(doubleJoin("%", DatatypeConverter.printHexBinary(str.getBytes(charset))));
	}
	public static String fromHtmlHex(String str){
		return new String(DatatypeConverter.parseHexBinary(str.replaceAll("%", "")), charset);
	}
	public static String doubleJoin(CharSequence del, String str){
		String[] elements = new String[str.length() / 2];
		for (int i = 1, j = 0; i < str.length(); i += 2){
			elements[j++] = str.substring(i - 1, i + 1);
		} 
		return String.join(del, elements);
	}
}
