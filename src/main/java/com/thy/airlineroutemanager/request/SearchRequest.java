package com.thy.airlineroutemanager.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchRequest {

    private String nameLike;
    private int pageSize;
}
