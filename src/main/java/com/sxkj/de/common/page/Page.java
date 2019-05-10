package com.sxkj.de.common.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 对mybatis-plus分页对象的拓展
 *
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {
    private int status = 0;
    private String msg = "";
}
