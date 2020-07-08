package com.prebeesh1427.movienameserviceprovider.data;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class CountryCode {

	private Set<String> countryCodes;
	
	public CountryCode(){
		countryCodes = new HashSet<>();
		countryCodes.add("in");
		countryCodes.add("us");
		countryCodes.add("uk");
		countryCodes.add("ar");
		countryCodes.add("at");
		countryCodes.add("au");
		countryCodes.add("be");
		countryCodes.add("br");
		countryCodes.add("ca");
		countryCodes.add("ch");
		countryCodes.add("cz");
		countryCodes.add("dk");
		countryCodes.add("de");
		countryCodes.add("ee");
		countryCodes.add("es");
		countryCodes.add("fr");
		countryCodes.add("hk");
		countryCodes.add("hu");
		countryCodes.add("ie");
		countryCodes.add("il");
		countryCodes.add("is");
		countryCodes.add("it");
		countryCodes.add("jp");
		countryCodes.add("kr");
		countryCodes.add("lt");
		countryCodes.add("lv");
		countryCodes.add("mx");
		countryCodes.add("nl");
		countryCodes.add("no");
		countryCodes.add("nz");
		countryCodes.add("ph");
		countryCodes.add("pl");
		countryCodes.add("pt");
		countryCodes.add("ro");
		countryCodes.add("ru");
		countryCodes.add("se");
		countryCodes.add("sg");
		countryCodes.add("sk");
		countryCodes.add("th");
		countryCodes.add("za");
	}
	
	public boolean contains(String code) {
		return countryCodes.contains(code);
	}
}
