package com.android.weatherapp;

public class MiscUtil {
    public static String[] CITY_TYPES = {"Small", "Medium", "Large"};

    //Seasons
    public static final String SEASON_SPRING = "Spring";
    public static final String SEASON_SUMMER = "Summer";
    public static final String SEASON_AUTUMN = "Autumn";
    public static final String SEASON_WINTER = "Winter";


    public enum Season {
        SEASON_SPRING("Spring"),
        SEASON_SUMMER("Summer"),
        SEASON_AUTUMN("Autumn"),
        SEASON_WINTER("Winter");

        private final String seasonName;

        Season(String seasonName) {
            this.seasonName = seasonName;
        }

        public String getSeasonName() {
            return seasonName;
        }

        public static Season fromString(String text) {
            for (Season season : Season.values()) {
                if (season.seasonName.equalsIgnoreCase(text)) {
                    return season;
                }
            }
            throw new IllegalArgumentException("Error: No constant matched" + text);
        }
    }
}
