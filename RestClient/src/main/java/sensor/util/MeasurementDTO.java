package sensor.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MeasurementDTO {
    private Double value;

    private Date createdAt;

    public MeasurementDTO() {
    }

    public MeasurementDTO(Double value, String createdAt) {
        this.value = value;
        this.createdAt = Date.from(LocalDateTime.parse(createdAt, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toInstant(ZoneOffset.of("+3")));
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
               "value=" + value +
               ", createdAt=" + createdAt +
               '}';
    }
}
