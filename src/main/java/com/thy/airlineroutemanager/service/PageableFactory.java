package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.request.DataGridRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


@Component
public class PageableFactory {

    public Pageable from(DataGridRequest request) {
        Sort sort = Sort.by(
                request.getSortDirection() == null
                        ? Sort.Direction.ASC
                        : request.getSortDirection(),
                request.getSortBy() == null
                        ? "id"
                        : request.getSortBy()
        );

        return PageRequest.of(request.getPage(), request.getPageSize(), sort);
    }
}
