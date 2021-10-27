package com.xseven.c4p.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.LogicDelete;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import com.xseven.c4p.common.util.MyCustomerInterface;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * UserEntity: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Data
@Accessors(
    chain = true
)
@EqualsAndHashCode(
    callSuper = false
)
@FluentMybatis(
    table = "user",
    schema = "c4p",
    defaults = MyCustomerInterface.class
)
public class UserEntity extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 用户唯一id
   */
  @TableId(
      value = "id",
      auto = false
  )
  private Long id;

  /**
   * 用户名称
   */
  @TableField("username")
  private String username;

  /**
   * 用户密码
   */
  @TableField("password")
  private String password;

  /**
   * 用户昵称
   */
  @TableField("nickname")
  private String nickname;

  /**
   * 删除标志 0未删除 1删除
   */
  @TableField(
      value = "is_deleted",
      insert = "0"
  )
  @LogicDelete
  private Boolean isDeleted;

  /**
   * 创建时间
   */
  @TableField(
      value = "gmt_created",
      insert = "now()"
  )
  private Date gmtCreated;

  /**
   * 更新时间
   */
  @TableField(
      value = "gmt_modified",
      insert = "now()",
      update = "now()"
  )
  private Date gmtModified;

  @Override
  public final Class entityClass() {
    return UserEntity.class;
  }
}
