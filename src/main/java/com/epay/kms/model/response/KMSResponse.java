package com.epay.kms.model.response;

import com.epay.kms.dto.ErrorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Class Name: ResponseDto
 * *
 * Description:
 * *
 * Author: V1018344(Rahul Prajapati)
 * Copyright (c) 2024 [State Bank of India]
 * ALl rights reserved
 * *
 * Version: 1.0
 */
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KMSResponse<T> {

    private List<T> data;
    private Integer status;
    private Long count;
    private Long total;
    private List<ErrorDto> errors;

    @Override
    public String toString() {
        return "ResponseDto{" +
                "status=" + status +
                ", data=" + data +
                ", count=" + count +
                ", total=" + total +
                ", errors=" + errors +
                '}';
    }
}
