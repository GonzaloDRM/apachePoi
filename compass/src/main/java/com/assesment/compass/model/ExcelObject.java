package com.assesment.compass.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelObject {

    private Integer contractId;
    private String name;
    private String name1;
    private String email;
    private Integer postalZip;
    private String address;

}
