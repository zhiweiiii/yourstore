package com.wade.yourstore.entity;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wade
 * @since 2021-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KeyInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Integer prefixKey;

    private Integer keyWord;

    private String content;

    private Boolean isUsing;

    private Integer flag;


}
