package com.data.service.serviceImpl;

import com.data.dao.BaseDAO;
import com.data.entity.User;
import com.data.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private BaseDAO baseDAO;

    @Override
    public List selectAllUser() {
        return baseDAO.findByHql("from User where 1=1",User.class);
    }
}
