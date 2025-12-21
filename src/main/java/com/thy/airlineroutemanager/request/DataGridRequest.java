package com.thy.airlineroutemanager.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Setter
@Getter
public class DataGridRequest {

    private Integer page = 1;
    private Integer pageSize = 10;
    private String sortBy = "id";
    private Sort.Direction sortDirection = Sort.Direction.ASC;
}
