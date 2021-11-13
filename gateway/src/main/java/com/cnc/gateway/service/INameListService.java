package com.cnc.gateway.service;


/**
 * 黑白名单服务
 *
 * @param <T>
 */
public interface INameListService<T> {

    boolean inAllowList(T t);

    boolean inDenyList(T t);

    Iterable<T> getAllowList();

    Iterable<T> getDenyList();
}
