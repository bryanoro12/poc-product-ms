package com.collabera.poc.product.service.impl;

import com.collabera.poc.product.common.dto.ActivityLog;
import com.collabera.poc.product.service.ActivityLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    /**
     * Convert ActivityLog to String
     * @param activityLog
     * @return
     */
    @Override
    @SneakyThrows
    public String convertActivityLogToString(final ActivityLog activityLog) {
        log.info("Converting ActivityLog to String...");
        return new ObjectMapper().writeValueAsString(activityLog);
    }
}
