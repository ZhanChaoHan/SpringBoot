package com.sinosoft.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysCode;
import com.sinosoft.normal.po.SysCodeKey;
import com.sinosoft.service.SysCodeService;
import com.sinosoft.dao.SysCodeMapper;

@Service
public class SysCodeServiceImpl implements SysCodeService {
	@Autowired
	public SysCodeMapper syscodemapper;

	@Override
	public int deleteByPrimaryKey(SysCodeKey key) {
		int row = 0;
		try {
			row = syscodemapper.deleteByPrimaryKey(key);
			System.out.println("row" + row);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("删除失败");
		}
		return row;
	}

	@Override
	public int insert(SysCode record) {
		int row = 0;
		try {
			row = syscodemapper.insert(record);
			System.out.println("信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("信息添加失败");
		}
		return row;
	}

	@Override
	public int insertSelective(SysCode record) {
		int row = 0;
		try {
			row = syscodemapper.insertSelective(record);
			System.out.println("信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("信息添加失败");
		}
		System.out.println("-----------------row=" + row);
		return row;
	}

	@Override
	public SysCode selectByPrimaryKey(SysCodeKey key) {
		SysCode code = syscodemapper.selectByPrimaryKey(key);
		return code;
	}

	@Override
	public int updateByPrimaryKeySelective(SysCode record) {
		int num = syscodemapper.updateByPrimaryKeySelective(record);
		return num;
	}

	@Override
	public int updateByPrimaryKey(SysCode record) {
		// TODO Auto-generated method stub
		int num = syscodemapper.updateByPrimaryKey(record);
		System.out.println("service" + num);
		return num;
	}

	@Override
	public ArrayList<SysCode> getSysCodeList(String CodeType) {
		return syscodemapper.getSysCodeList(CodeType);
	}

	@Override
	public ArrayList<SysCode> selectByPrimaryKeyList(SysCode record) {

		return syscodemapper.selectByPrimaryKeyList(record);
	}

	@Override
	public ArrayList<SysCode> getCodeTypeList(String CodeType, String ComCode) {
		// TODO Auto-generated method stub
		return syscodemapper.getCodeTypeList(CodeType, ComCode);
	}

	@Override
	public ArrayList<SysCode> muchComLevelList(String CodeType, String ComCode) {
		// TODO Auto-generated method stub
		return syscodemapper.muchComLevelList(CodeType, ComCode);
	}

	@Override
	public ArrayList<SysCode> thanComLevelList(String CodeType, String ComCode) {
		// TODO Auto-generated method stub
		return syscodemapper.thanComLevelList(CodeType, ComCode);
	}
}
