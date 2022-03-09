package com.dayosoft.excel.repository;

import com.dayosoft.excel.model.ReportPerformance;
import io.jsondb.JsonDBTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class ReportPerformanceRepository {

    private final JsonDBTemplate jsonDBTemplate;

    @PostConstruct
    public void createCollection() {
        if (!jsonDBTemplate.collectionExists(ReportPerformance.class)) {
            jsonDBTemplate.createCollection(ReportPerformance.class);
        }
    }

    public List<ReportPerformance> list() {
        return jsonDBTemplate.findAll(ReportPerformance.class);
    }

    public void add(ReportPerformance reportPerformance) {
        reportPerformance.setId(UUID.randomUUID().toString());
        jsonDBTemplate.insert(reportPerformance);
    }

    public void delete(ReportPerformance reportPerformance) {
        jsonDBTemplate.remove(reportPerformance, ReportPerformance.class);
    }

    public List<ReportPerformance> findByName(String name) {
        String jxQuery = String.format("/.[templateName='%s']", name);
        return jsonDBTemplate.find(jxQuery, ReportPerformance.class);
    }

}
