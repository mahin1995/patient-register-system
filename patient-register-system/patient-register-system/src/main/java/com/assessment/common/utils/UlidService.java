package com.assessment.common.utils;

import de.huxhorn.sulky.ulid.ULID;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class UlidService {

    public static String getUlid() {
        ULID ulid = new ULID();
        long timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        ULID.Value value = ulid.nextValue(timestamp);
        return value.toString();
    }

    public Instant extractTimestampFromUlid(String ulidString) {
        ULID.Value value = ULID.parseULID(ulidString);
        return Instant.ofEpochMilli(value.timestamp());
    }

}
