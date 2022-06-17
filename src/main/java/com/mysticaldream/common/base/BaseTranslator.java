package com.mysticaldream.common.base;

import java.util.List;

/**
 * 基础类型转化 s源,t目标
 *
 * @description: BaseMapper
 * @date: 2022/5/30 10:28
 * @author: MysticalDream
 */

public interface BaseTranslator<S, T> {
    /**
     * 目标型转化成源类型
     *
     * @param target
     * @return
     */
    S toSource(T target);

    /**
     * 源类型转化成目标类型
     *
     * @param source
     * @return
     */
    T toTarget(S source);

    /**
     * 目标类型列表转成源类型列表
     *
     * @param targetList
     * @return
     */
    List<S> toSources(List<T> targetList);

    /**
     * 源类型列表转化成目标类型列表
     *
     * @param sourceList
     * @return
     */
    List<T> toTargets(List<S> sourceList);
}
