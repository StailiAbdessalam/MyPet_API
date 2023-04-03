package com.mypet.back_end.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptionContractRequest {
    private String referencePerson;
    private String referencePost;
}
