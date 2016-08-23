/**
 * Copyright (c) 2016, Stupid Bird and/or its affiliates. All rights reserved.
 * STUPID BIRD PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @Project : parent
 * @Package : net.lizhaoweb.datasource.mysql.etago.mapper.read
 * @author <a href="http://www.lizhaoweb.net">李召(John.Lee)</a>
 * @Time : 17:05
 */
package net.lizhaoweb.datasource.mysql.etago.mapper.read;

import net.lizhaoweb.datasource.mysql.etago.model.GoodsTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author <a href="http://www.lizhaoweb.cn">李召(John.Lee)</a>
 * @notes Created on 2016年07月29日<br>
 * Revision of last commit:$Revision$<br>
 * Author of last commit:$Author$<br>
 * Date of last commit:$Date$<br>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/schema/spring/spring-mysql_etago-model.xml", "classpath*:/schema/spring/spring-mysql_etago-datasource.xml", "classpath*:/schema/spring/spring-mysql_etago-mapper.xml"})
public class TestGoodsTagsReadMapper {

    @Autowired
    private IGoodsTagsReadMapper readMapper;

    @Test
    public void getAll() {
        List<GoodsTags> goodList = readMapper.getAll(-1);
        System.out.println(goodList);
        System.out.println();
        System.out.println();

        for (GoodsTags goods : goodList) {
            System.out.println(goods.getTag());
        }
    }
}
