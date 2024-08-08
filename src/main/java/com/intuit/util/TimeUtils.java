package com.intuit.util;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.Instant;

@UtilityClass
public class TimeUtils {
    private static final long FIFTEEN_MINUTES_IN_MILLIS = 15 * 60 * 1000;
    public static Long getCurrentTimeStamp(){
        return Instant.now().toEpochMilli();
    }

    public static long calculateDifferenceInHours(long epochStart, long epochEnd) {
        // Convert epoch seconds to Instant
        Instant start = Instant.ofEpochMilli(epochStart);
        Instant end = Instant.ofEpochMilli(epochEnd);

        // Calculate the duration between the two Instants
        Duration duration = Duration.between(start, end);

        // Get the difference in hours
        return duration.toHours();
    }

    public static Long addFifteenMinutes(Long epochTimestamp) {
        return epochTimestamp + FIFTEEN_MINUTES_IN_MILLIS;
    }

}
