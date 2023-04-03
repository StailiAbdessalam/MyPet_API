package com.mypet.back_end.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptionContractResponse {
    private Long id;
    private PersonResponse person;
    private PostResponse post;
}
