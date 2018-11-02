package com.sinosoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysCompany;
import com.sinosoft.normal.vo.SysCompanyVo;
import com.sinosoft.service.SysCompanyService;
import com.sinosoft.dao.SysCompanyMapper;

@Service
public class SysCompanyServiceImpl  implements SysCompanyService {
	@Autowired
	private SysCompanyMapper mapper;

	@Override
	public int deleteByPrimaryKey(String comCode) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(comCode);
	}

	@Override
	public int insert(SysCompany record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(SysCompany record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public SysCompany selectByPrimaryKey(String comCode, String validStatus) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(comCode, validStatus);
	}

	@Override
	public int updateByPrimaryKeySelective(SysCompany record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(SysCompany record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysCompanyVo> querySysCompany(String comCode, String comCname, String areaCode, String comAddress,
			String admin, String validStatus) {
		// TODO Auto-generated method stub
		// String mappath = "esmapper/xiaoman.xml";
		// ClientInterface clientUtil =
		// ElasticSearchHelper.getConfigRestClientUtil(mappath);
		// //加载配置文件，创建es客户端工具包
		//
		// Map<String,Object> params = new HashMap<String,Object>();
		// params.put("xiaoManName","小曼曼5");
		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// try {
		// params.put("startTime",dateFormat.parse("2018-06-02 00:00:00").getTime());
		// params.put("endTime",new Date().getTime());
		// params.put("from", "1");
		// params.put("size", "10");
		// params.put("max", "xiaoManId");
		// params.put("min", "xiaoManId");
		// params.put("sum", "xiaoManId");
		// params.put("keyword", "xiaoManName"+".keyword");
		// BatchPolymerizaTionUtils BatchPolymerizaTionUtils=new
		// BatchPolymerizaTionUtils();
		//
		// List<BatchEntityUtils> list=BatchPolymerizaTionUtils.SingularUpdate(mappath,
		// "_search", "queryAvgmiman", params, XiaoMan.class, "applicationsums");
		// System.out.println(list);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return mapper.querySysCompany(comCode, comCname, areaCode, comAddress, admin, validStatus);
	}

	@Override
	public List<SysCompany> selectSysCompany(String admin) {
		// TODO Auto-generated method stub
		return mapper.selectSysCompany(admin);
	}

	@Override
	public List<SysCompany> selectSupSysCompany(String comType) {
		// TODO Auto-generated method stub
		return mapper.selectSupSysCompany(comType);
	}

	@Override
	public SysCompany sysCompanyId(String codeCome) {
		// TODO Auto-generated method stub
		return mapper.sysCompanyId(codeCome);
	}

	@Override
	public List<SysCompany> checkSysCompany(String comCode) {
		// TODO Auto-generated method stub
		return mapper.checkSysCompany(comCode);
	}

	@Override
	public SysCompany getSysCompanyById(String supComd) {
		// TODO Auto-generated method stub
		return mapper.getSysCompanyById(supComd);
	}

	@Override
	public List<SysCompany> findSysCompanyList(String comCode, String reverse1) {
		// TODO Auto-generated method stub
		return mapper.findSysCompanyList(comCode, reverse1);
	}

}
