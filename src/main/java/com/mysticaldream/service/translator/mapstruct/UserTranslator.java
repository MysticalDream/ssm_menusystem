package com.mysticaldream.service.translator.mapstruct;

import com.mysticaldream.common.base.BaseTranslator;
import com.mysticaldream.domain.User;
import com.mysticaldream.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @description: UserMapper
 * @date: 2022/5/30 10:29
 * @author: MysticalDream
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserTranslator extends BaseTranslator<User, UserDTO> {

}
