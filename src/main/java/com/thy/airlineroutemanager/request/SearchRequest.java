package com.thy.airlineroutemanager.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchRequest extends DataGridRequest {

    @NotNull(message = "Search text cannot be null.")
    private String nameLike;
}
