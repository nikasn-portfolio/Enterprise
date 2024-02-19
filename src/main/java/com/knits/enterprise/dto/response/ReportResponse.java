package com.knits.enterprise.dto.response;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse<T> {
    private T parent;
    Map<Long, ReportDto> reports;

}
