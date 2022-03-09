package com.dayosoft.excel.model;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import lombok.*;
import java.time.LocalDateTime;

@Document(collection = "reportPerformance", schemaVersion= "1.0")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReportPerformance {

    @Id
    private String id;
    private String templateName;
    private String data;
    private Integer reportCount;
    private LocalDateTime executedDateTime;
    private long memoryInBytes;
    private long memoryInMB;
    private long elapsedTimeInMS;

}
