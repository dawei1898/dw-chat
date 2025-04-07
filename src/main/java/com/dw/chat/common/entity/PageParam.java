package com.dw.chat.common.entity;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 分页查询参数
 *
 * @author dawei
 */
@Data
public class PageParam<T> implements Serializable {
	
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 当前页(从1开始)；若为0，则不分页，获取全部
     */
    @Min(value = 0, message = "pageNum最小值为0")
    protected Integer pageNum = 1;

    /**
     * 每页数据条数
     */
    @Min(value = 1, message = "pageSize最小值为1")
    protected Integer pageSize = 10;

    /**
     * 附带的查询参数
     */
    @Valid
    protected T param;



    public  int buildStartNum(){
        if (pageNum == 0) {
            return pageNum;
        }
        return  (pageNum - 1) * pageSize + 1;
    }

    public  int buildEndNum(){
        return  pageNum * pageSize;
    }


}
