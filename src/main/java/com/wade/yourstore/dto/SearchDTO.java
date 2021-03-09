package com.wade.yourstore.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class SearchDTO<T> extends Page{

   private T searchData;

}
