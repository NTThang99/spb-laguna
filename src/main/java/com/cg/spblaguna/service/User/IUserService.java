package com.cg.spblaguna.service.User;

import com.cg.spblaguna.model.User;
import com.cg.spblaguna.service.IGeneralService;

public interface IUserService extends IGeneralService<User,Long> {
    User findByFullName(String fullName);

}
