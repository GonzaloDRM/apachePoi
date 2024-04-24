package com.assesment.compass.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutputRepeated {

    private Integer contractIdSource;
    private Integer contractIdMatch;
    private String Accuracy;

}
