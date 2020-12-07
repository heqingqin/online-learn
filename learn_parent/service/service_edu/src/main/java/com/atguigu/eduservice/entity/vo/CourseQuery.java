package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hdoubleq
 * @Date 2020/11/29-17:00
 */
@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程标题,模糊查询")
    private String title;
    @ApiModelProperty(value = "课程状态,模糊查询，Draft：未发布,Normal:已发布")
    private String status;
}
