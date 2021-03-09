package com.wade.yourstore.entity;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * 
 * </p>
 *
 * @author wade
 * @since 2021-02-18
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class KeyInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    private Long id;

    @Column
    private Integer prefixKey;

    @Column
    private Integer keyWord;

    @Column
    private String content;

    @Column
    private Boolean isUsing;

    @Column
    private Integer flag;


}
