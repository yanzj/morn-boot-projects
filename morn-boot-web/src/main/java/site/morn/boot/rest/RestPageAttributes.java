package site.morn.boot.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import site.morn.core.CriteriaAttributes;
import site.morn.rest.RestPageableAttributes;

/**
 * REST分页请求
 *
 * @author timely-rain
 * @since 1.0.0, 2018/7/10
 */
public interface RestPageAttributes<P extends RestPageableAttributes, M> {

  /**
   * 获取REST分页参数
   *
   * @return REST分页参数
   */
  P getPageable();

  /**
   * 设置REST分页参数
   *
   * @param pageable 分页参数
   * @return REST分页请求
   */
  RestPageAttributes<P, M> setPageable(P pageable);

  /**
   * 获取数据模型
   *
   * @return 数据模型
   */
  M getModel();

  /**
   * 设置数据模型
   *
   * @param model 数据模型
   * @return REST分页请求
   */
  RestPageAttributes<P, M> setModel(M model);

  /**
   * 获取附加数据
   *
   * @return 附加数据
   */
  CriteriaAttributes getAttach();

  /**
   * 设置附加数据
   *
   * @param attach 附加数据
   * @return REST分页请求
   */
  RestPageAttributes<P, M> setAttach(CriteriaAttributes attach);

  /**
   * 生成JPA分页请求
   *
   * @return JPA分页请求
   */
  default PageRequest generatePageRequest() {
    RestPageableAttributes pageable = getPageable();
    return new PageRequest(pageable.getPage(), pageable.getSize());
  }

  /**
   * 生成JPA分页请求
   *
   * @return JPA分页请求
   */
  default PageRequest generatePageRequest(Sort sort) {
    RestPageableAttributes pageable = getPageable();
    return new PageRequest(pageable.getPage(), pageable.getSize(), sort);
  }
}
