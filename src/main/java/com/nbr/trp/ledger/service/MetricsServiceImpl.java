package com.nbr.trp.ledger.service;

import com.nbr.trp.ledger.entity.Metrics;
import com.nbr.trp.ledger.repository.MetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MetricsServiceImpl implements MetricsService{
    @Autowired
    private final MetricsRepository metricsRepository;

    public MetricsServiceImpl(MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }

    @Override
    public Metrics saveMetrics(Metrics metrics) {

        Metrics metrics1 = metricsRepository.findByAssessmentYearAndSlotNo(metrics.getAssessmentYear(),metrics.getSlotNo());
        return metricsRepository.save(metrics);
    }

    @Override
    public List<Metrics> getAllRatesOfAssessmentYear(String year) {
        return null;
    }

    @Override
    public List<Metrics> getAllRatesOfFiscalYear(String year) {
        return null;
    }

    @Override
    public Metrics getByMid(String mid) {
        return null;
    }
}
