/**
 * Copyright (c) 2016, Stupid Bird and/or its affiliates. All rights reserved.
 * STUPID BIRD PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @Project : Savor DataSource Mysql Etago
 * @Title : IGoodsReadMapper.java
 * @Package : net.lizhaoweb.datasource.mysql.etago.mapper.read
 * @author <a href="http://www.lizhaoweb.net">李召(John.Lee)</a>
 * @Date : 2016年6月30日
 * @Time : 下午4:04:55
 */
package net.lizhaoweb.datasource.mysql.etago.mapper.read;

import net.lizhaoweb.datasource.mysql.etago.model.Goods;

import java.util.List;

/**
 * <h1>持久层 - 产品读取</h1>
 *
 * @author <a href="http://www.lizhaoweb.cn">李召(John.Lee)</a>
 * @version 1.0.0.0.1
 * @notes Created on 2016年6月30日.<br>
 * Revision of last commit:$Revision$.<br>
 * Author of last commit:$Author$.<br>
 * Date of last commit:$Date$.<br>
 *
 */
public interface IGoodsReadMapper {

    /**
     * 获取所有产品。
     *
     * @return 返回产品列表。
     */
    List<Goods> getAll();

    /**
     * 获取所有产品。
     *
     * @return 返回产品列表。
     */
    List<Goods> getAll2();
}
