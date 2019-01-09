package com.n3n4.im.imcore.gateway.service;

/**
 * 报文缓存服务
 *  终端发送的部分报文如：心跳，对于同一个channel来说是一样的，这时候，可以将这种报文加在PackageCache里，下次直接比较，如果equal则：不解析，直接跳到业务处理
 *  这样做希望能节省大部分的重复解析过程
 *  *但是其实有消息序列号的原因，不可能消息都一样
 */
public class PackageCacheService {
}
