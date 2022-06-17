package com.mysticaldream.web.translator.mapstruct;

import com.mysticaldream.common.base.BaseTranslator;
import com.mysticaldream.domain.User;
import com.mysticaldream.web.vo.LoginUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 用户注册表单视图转化
 *
 * @description: UserRigisterVOTranslator
 * @date: 2022/6/5 12:16
 * @author: MysticalDream
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoginUserVOTranslator extends BaseTranslator<User,LoginUserVO> {

}
