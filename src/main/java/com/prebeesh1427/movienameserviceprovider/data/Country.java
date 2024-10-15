package com.prebeesh1427.movienameserviceprovider.data;

import java.util.Optional;

public enum Country {
    PORTUGAL("pt"),
    MALAYSIA("my"),
    UKRAINE("ua"),
    SLOVAKIA("sk"),
    MEXICO("mx"),
    UNITED_STATES("us"),
    AUSTRALIA("au"),
    IRELAND("ie"),
    AUSTRIA("at"),
    ITALY("it"),
    NORWAY("no"),
    ARGENTINA("ar"),
    RUSSIA("ru"),
    ISRAEL("il"),
    SOUTH_KOREA("kr"),
    ROMANIA("ro"),
    PHILIPPINES("ph"),
    POLAND("pl"),
    CHILE("cl"),
    SWITZERLAND("ch"),
    FRANCE("fr"),
    INDIA("in"),
    ECUADOR("ec"),
    INDONESIA("id"),
    COLOMBIA("co"),
    NORTH_MACEDONIA("mk"),
    PERU("pe"),
    SLOVENIA("si"),
    BELGIUM("be"),
    FINLAND("fi"),
    AZERBAIJAN("az"),
    SPAIN("es"),
    GREECE("gr"),
    THAILAND("th"),
    MOLDOVA("md"),
    UNITED_KINGDOM("gb"),
    CANADA("ca"),
    NETHERLANDS("nl"),
    DENMARK("dk"),
    TURKEY("tr"),
    UNITED_ARAB_EMIRATES("ae"),
    BRAZIL("br"),
    SWEDEN("se"),
    HONG_KONG("hk"),
    HUNGARY("hu"),
    VIETNAM("vn"),
    CYPRUS("cy"),
    SINGAPORE("sg"),
    LITHUANIA("lt"),
    JAPAN("jp"),
    PANAMA("pa"),
    NEW_ZEALAND("nz"),
    ESTONIA("ee"),
    CROATIA("hr"),
    GERMANY("de"),
    ICELAND("is"),
    BULGARIA("bg"),
    SOUTH_AFRICA("za"),
    CZECH_REPUBLIC("cz"),
    SERBIA("rs");

    private final String countryCode;

    Country(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public static Optional<Country> fromCode(String code) {
        for (Country country : values()) {
            if (country.getCountryCode().equalsIgnoreCase(code)) {
                return Optional.of(country);
            }
        }
        return Optional.empty();
    }
}

