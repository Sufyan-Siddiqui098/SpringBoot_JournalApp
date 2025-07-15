package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public static class Current {

        @JsonProperty("last_updated")
        public String lastUpdated;
        @JsonProperty("temp_c")

        public double tempF;
        @JsonProperty("is_day")
        public int isDay;

        @JsonProperty("feelslike_c")
        public double feelslikeC;

    }




}



