package com.prebeesh1427.movienameserviceprovider.data;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Component
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CountryCode {
	private Set<String> countryCodes = createDefaultCountryCodes();

	public boolean isSupported(String code) {
		return countryCodes.contains(code);
	}

	public Set<String> getCountryCodes() {
		return countryCodes;
	}
	
	private static Set<String> createDefaultCountryCodes() {
		return Set.of("in", "us", "uk", "ar", "at", "au", "be", "br", "ca", "ch", "cz", "dk", "de", "ee", "es", "fr", "hk", "hu", "ie", "il", "is", "it", "jp", "kr", "lt", "lv", "mx", "nl", "no", "nz", "ph", "pl", "pt", "ro", "ru", "se", "sg", "sk", "th", "za");
	}
}
