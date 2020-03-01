package com.xp.glasses.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mrxiong
 */
@Data
public class ReturnData<T> {

    private List<T> rows = new ArrayList<T>();

    private int total;
}
