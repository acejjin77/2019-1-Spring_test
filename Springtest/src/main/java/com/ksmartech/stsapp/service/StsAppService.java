package com.ksmartech.stsapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ksmartech.stsapp.dao.StsAppDao;
import com.ksmartech.stsapp.model.UserCreateReqDto;
import com.ksmartech.stsapp.model.UserCreateResDto;
import com.ksmartech.stsapp.model.UserDeleteResDto;
import com.ksmartech.stsapp.model.UserModifyReqDto;
import com.ksmartech.stsapp.model.UserModifyResDto;
import com.ksmartech.stsapp.model.UserReadListResDto;
import com.ksmartech.stsapp.model.UserReadResDto;

@Service
public class StsAppService {
	private static final Logger logger = LoggerFactory.getLogger(StsAppService.class);
	
	@Autowired
	private StsAppDao dao;
	
	public UserCreateResDto userCreate(UserCreateReqDto reqDto, Model model) {
		logger.debug("userCreate");
		
		String userId = "UID" + new Date().getTime();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("userName", reqDto.getUserName());
		map.put("phoneNumber", reqDto.getPhoneNumber());
		
		int insertuseInfoCnt = 0;
		
		try {
			insertuseInfoCnt = dao.insertUserInfo(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("insertUserInfoCnt : " + insertuseInfoCnt);
				
		UserCreateResDto resDto = new UserCreateResDto();
		resDto.setUserId(userId);
		
		return resDto;
	}
	public UserReadResDto userRead(String userId, Model model) {
		logger.debug("userRead");
		
		HashMap<String, Object> outmap = null;
		
		try {
			outmap = dao.selectUserInfo(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}

		UserReadResDto resDto = new UserReadResDto();
		resDto.setUserName(outmap.get("userName").toString());
		resDto.setPhoneNumber(outmap.get("phoneNumber").toString());
		return resDto;
	}
	
	public ArrayList<UserReadListResDto> userList(Model model) {
		logger.debug("userList");
		
		ArrayList<UserReadListResDto> list = new ArrayList<UserReadListResDto>();
		ArrayList<UserReadListResDto> outlist = null;
		
		try {
			outlist = dao.selectUserList();
			logger.debug(outlist.toString());
			
			for (UserReadListResDto resDto :outlist) {
				logger.debug(resDto.toString());
				list.add(resDto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public UserModifyResDto userModify(UserModifyReqDto reqDto, String userId, Model model) {
		logger.debug("userModify");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("userName", reqDto.getUserName().toString());
		map.put("phoneNumber", reqDto.getPhoneNumber().toString());
		
		int updateUserInfoCnt = 0;
		try {
			updateUserInfoCnt = dao.updateUserInfo(map);
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("updateUserInfoCnt " + updateUserInfoCnt);
		UserModifyResDto resDto = new UserModifyResDto();
		
		if (updateUserInfoCnt == 1) {
			resDto.setSuccess(true);
		}
		return resDto;
	}
	public UserDeleteResDto userDelete(String userId, Model model) {
		logger.debug("userDelete");
		
		int deleteUserInfoCnt = 0;
		try {
			deleteUserInfoCnt = dao.deleteUserInfo(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("deleteUserInfoCnt: " + deleteUserInfoCnt);
		
		UserDeleteResDto resDto = new UserDeleteResDto();
		
		if(deleteUserInfoCnt == 1) {
			resDto.setSuccess(true);
		}
		return resDto;
	}
}
