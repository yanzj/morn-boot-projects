package site.morn.boot.jpa;

import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;
import site.morn.core.CriteriaMap;

/**
 * JPA通用对象
 *
 * @author timely-rain
 * @since 1.0.0, 2019/1/23
 */
@Accessors(chain = true, fluent = true)
@Getter
@Setter
public class JpaCommon<M> {

  private M model;

  private CriteriaMap attach;

  private JpaParameter<M> parameter;

  private Path<?> path;

  private CriteriaQuery<?> query;

  private CriteriaBuilder builder;

  private JpaReference reference;

  private JpaPredicate predicate;

  private JpaBatchCondition condition;

  public JpaCommon<M> attach(Map<String, Object> attach) {
    this.attach = new CriteriaMap(attach);
    return this;
  }

  /**
   * 构建JPA查询参数
   *
   * @return JPA查询参数
   */
  public JpaParameter<M> buildParameter() {
    Assert.notNull(model, "model is null.");
    Assert.notNull(attach, "attach is null.");
    parameter = new JpaParameterSupport<M>().model(model).attach(this.attach);
    return parameter;
  }

  /**
   * 构建JPA原生引用
   *
   * @return JPA原生引用
   */
  public JpaReference buildReference() {
    Assert.notNull(path, "path is null.");
    Assert.notNull(query, "query is null.");
    Assert.notNull(builder, "builder is null.");
    reference = new JpaReference(path, query, builder);
    return reference;
  }

  /**
   * 构建JPA查询断言
   *
   * @return JPA查询断言
   */
  public JpaPredicate buildPredicate() {
    Assert.notNull(path, "path is null.");
    Assert.notNull(query, "query is null.");
    Assert.notNull(builder, "builder is null.");
    predicate = new JpaPredicate(builder);
    return predicate;
  }

  /**
   * 构建JPA查询条件
   *
   * @return JPA查询条件
   */
  public JpaBatchCondition buildCondition() {
    Assert.notNull(path, "path is null.");
    Assert.notNull(query, "query is null.");
    Assert.notNull(builder, "builder is null.");
    Assert.notNull(parameter, "parameter is null.");
    condition = new JpaConditionSupport<M>().path(path).query(query).builder(builder)
        .parameter(parameter);
    return condition;
  }
}
