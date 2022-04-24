package com.wang.rpc.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author C.Wang
 * @CreateTime 2022/4/24 19:48
 */

@Data
@AllArgsConstructor
public class HelloObject implements Serializable {
    private Integer id;
    private String message;
}
