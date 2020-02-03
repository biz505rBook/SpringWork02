package com.biz.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.ems.domain.EmailVO;

public interface MybatisEmailDao {
	@Select("select * from tbl_ems")
	public List<EmailVO> selectAll();
	
	@Select("${create_table}")
	public void createTable(String create_table);
}