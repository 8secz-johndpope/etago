/**
 * Copyright (c) 2016, Stupid Bird and/or its affiliates. All rights reserved.
 * STUPID BIRD PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @Project : Savor DataSource Mysql Etago
 * @Title : User4CloudSearch.java
 * @Package : net.lizhaoweb.datasource.mysql.etago.model
 * @author <a href="http://www.lizhaoweb.net">李召(John.Lee)</a>
 * @Date : 2016年7月5日
 * @Time : 下午5:42:49
 */
package net.lizhaoweb.datasource.mysql.etago.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <h1>模型(云搜索) - 用户</h1>
 *
 * @author <a href="http://www.lizhaoweb.cn">李召(John.Lee)</a>
 * @version 1.0.0.0.1
 * @notes Created on 2016年7月5日.<br>
 * Revision of last commit:$Revision$.<br>
 * Author of last commit:$Author$.<br>
 * Date of last commit:$Date$.<br>
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User4CloudSearch extends Abstract4CloudSearch {

    private static final long serialVersionUID = 3566039596822828746L;

    /**
     * 用户id
     */
    @JsonProperty(value = "user_id")
    private Integer userId;

    /**
     * 设备号
     */
    @JsonProperty(value = "device_id")
    private String deviceId;

    /**
     * 登录来源0:iphone1:Android
     */
    @JsonProperty(value = "where_from")
    private Integer whereFrom;

    //*/
    @JsonProperty(value = "traceinfo")
    private String traceInfo;

    /**
     * 当前位置经度
     */
    @JsonIgnore
    private Double longitude;

    /**
     * 当前位置维度
     */
    @JsonIgnore
    private Double latitude;

    /**
     * ip地址
     */
    @JsonProperty(value = "ip")
    private String ip;

    /**
     * 登录时间
     */
    @JsonProperty(value = "add_time")
    private Long addTime;

    /**
     * 用户姓名
     */
    @JsonProperty(value = "username")
    private String username;

    /**
     * 登录密码
     */
    @JsonProperty(value = "password")
    private String password;

    /**
     * 国家、地区代码
     */
    @JsonProperty(value = "region_code")
    private Integer regionCode;

    /**
     * 用户昵称
     */
    @JsonProperty(value = "nickname")
    private String nickName;

    /**
     * 手机号码
     */
    @JsonProperty(value = "mobile")
    private String mobile;

    /**
     * Email地址
     */
    @JsonProperty(value = "email")
    private String email;

    /**
     * 用户头像
     */
    @JsonProperty(value = "face")
    private String face;

    /**
     * 性别:0, 保密; 1, 男; 2, 女;
     */
    @JsonProperty(value = "gender")
    private Integer gender;

    /**
     * 用户类型1：普通用户2：官方账号
     */
    @JsonProperty(value = "type")
    private Integer type;

    /**
     * 注册时间
     */
    @JsonProperty(value = "reg_time")
    private Long regTime;

    /**
     * 用户来源1:iphone 2:Android 3:后台添加
     */
    @JsonProperty(value = "user_where_from")
    private Integer userWhereFrom;

    /**
     * 用户来源描述
     */
    @JsonProperty(value = "where_from_extra")
    private String whereFromExtra;

    /**
     * 修改时间
     */
    @JsonProperty(value = "update_time")
    private Long updateTime;

    /**
     * 删除标识,默认为0,未删除; 1, 已删除;
     */
    @JsonProperty(value = "del_flag")
    private Integer delFlag;

    /**
     * 发布产品数量
     */
    @JsonProperty(value = "goods_count")
    private Integer goodsCount;

    /**
     * 粉丝数量
     */
    @JsonProperty(value = "fans_count")
    private Integer fansCount;

    /**
     * 点赞数
     */
    @JsonProperty(value = "like_count")
    private Integer likeCount;

    /**
     * 评论数
     */
    @JsonProperty(value = "comment_count")
    private Integer commonCount;

    /**
     * 位置
     */
    @JsonProperty("location")
    public String getLocation() {
        String location = "";
        if (this.latitude != null && this.longitude != null) {
            location = String.format("%s, %s", this.latitude, this.longitude);
        }
        return location;
    }
}
