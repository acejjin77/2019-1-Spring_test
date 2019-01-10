package com.ksmartech.stsapp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ksmartech.stsapp.model.UserCreateReqDto;
import com.ksmartech.stsapp.model.UserCreateResDto;
import com.ksmartech.stsapp.model.UserReadListResDto;

@Service
public class StsAppDao extends SqlSessionDaoSupport {
	private static final Logger logger = LoggerFactory.getLogger(StsAppDao.class);
	
	public int insertUserInfo(HashMap<String, Object> map) throws Exception {
		logger.debug(map.toString());
		return getSqlSession().insert("sts.insertUserInfo", map);
	}
	
	public HashMap<String, Object> selectUserInfo(String userId) throws Exception {
		logger.debug(userId);
		return (HashMap<String, Object>) getSqlSession().selectOne("sts.selectUserInfo", userId);
	}

	public ArrayList<UserReadListResDto> selectUserList() throws Exception {
		return (ArrayList<UserReadListResDto>) getSqlSession().selectList("sts.selectUserList");
	}

	public int updateUserInfo(HashMap<String, Object> map) {
		logger.debug(map.toString());
		return getSqlSession().update("sts.updateUserInfo", map);
	}

	public int deleteUserInfo(String userId) throws Exception {
		logger.debug(userId);
		return getSqlSession().update("sts.deleteUserInfo", userId);
	}
	
}
