package com.biz.todo.respository;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.todo.domain.TestVO;

public interface TestDao {
	@Select("select * from tbl_test")
	public List<TestVO> selectAll();
	
	@Update("insert into tbl_test( "
			+ "T_SEQ, " + 
			"T_TITLE, " + 
			"T_CONTENT) values ( "
			+ "seq_test.nextval, " + 
			"#{t_title,jdbcType=VARCHAR}, " + 
			"#{t_content,jdbcType=VARCHAR} "
			+ ") ")
	public int insert(TestVO testVO);
}
