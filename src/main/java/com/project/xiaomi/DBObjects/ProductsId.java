package com.project.xiaomi.DBObjects;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductsId implements Serializable {
    String modelName;
    String partName;
}
