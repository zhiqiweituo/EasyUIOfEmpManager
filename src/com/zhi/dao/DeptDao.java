package com.zhi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhi.model.Dept;
import com.zhi.model.PageBean;
import com.zhi.util.StringUtil;

public class DeptDao {
	//MyBatis
	public ResultSet deptList(Connection conn,PageBean pageBean,Dept dept)throws Exception{
		StringBuffer sb=new StringBuffer("select deptno,dname,loc from dept");
		if(dept!=null && StringUtil.isNotEmpty(dept.getDname())){
			sb.append(" and dname like '%"+dept.getDname()+"%'");
		}
		if(dept!=null && StringUtil.isNotEmpty(dept.getLoc())){
			sb.append(" and loc like '%"+dept.getLoc()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int deptCount(Connection conn,Dept dept)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from dept");
		if(dept!=null && StringUtil.isNotEmpty(dept.getDname())){
			sb.append(" and dname like '%"+dept.getDname()+"%'");
		}
		if(dept!=null && StringUtil.isNotEmpty(dept.getLoc())){
			sb.append(" and loc like '%"+dept.getLoc()+"%'");
		}
		PreparedStatement pstmt=conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/**
	 * delete from tableName where field in (1,3,5)
	 * @param conn
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public int deptDelete(Connection conn,String delIds)throws Exception{
		/**
		 * 实际项目不建议使用in
		 */
		String sql="delete from dept where deptno in("+delIds+")";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	public int deptAdd(Connection conn,Dept dept)throws Exception{
		String sql="insert into dept values(?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, dept.getDeptno());
		pstmt.setString(2, dept.getDname());
		pstmt.setString(3, dept.getLoc());
		return pstmt.executeUpdate();
	}
	
	public int deptModify(Connection conn,Dept dept)throws Exception{
		String sql="update dept set dname=?,loc=? where deptno=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, dept.getDname());
		pstmt.setString(2, dept.getLoc());
		pstmt.setInt(3, dept.getDeptno());
		return pstmt.executeUpdate();
	}
	
	public int findMaxDeptno(Connection conn) throws SQLException{
		//选出数据库中最大的部门编号
		String sql="select max(deptno) as maxDeptno from dept";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("maxDeptno");
		}else{
			return 0;
		}
	}
	
}
