package com.zhi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhi.model.Emp;
import com.zhi.model.PageBean;
import com.zhi.util.DateUtil;

public class EmpDao {
	
	public ResultSet empList(Connection conn,PageBean pageBean,Emp searchEmp) throws SQLException{
		StringBuffer sb=new StringBuffer("select empno,ename,job,mgr,hiredate,sal,comm,t1.deptno,t2.dname "
				+ " from emp t1,dept t2 where t1.deptno=t2.deptno");
		//条件搜索
		if(searchEmp.getEmpno()!=null){//因为是包装类，无默认值，直接判断是否为空
			sb.append(" and t1.empno like '%"+searchEmp.getEmpno()+"%'");
		}
		if(searchEmp.getEname()!=null){
			sb.append(" and t1.ename like '%"+searchEmp.getEname()+"%'");
		}
		if(searchEmp.getDeptno()!=null){
			sb.append(" and t1.deptno ='"+searchEmp.getDeptno()+"'");
		}
		
		sb.append(" order by empno asc");
		
		//分页
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pst=conn.prepareStatement(sb.toString());
		return pst.executeQuery();
	}
	public int empCount(Connection conn,Emp searchEmp)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from emp t1,dept t2 where t1.deptno=t2.deptno");
		
		//条件搜索(复制上一个的，与list里的判断一致)
		if(searchEmp.getEmpno()!=null){//因为是包装类，无默认值，直接判断是否为空
			sb.append(" and t1.empno like '%"+searchEmp.getEmpno()+"%'");
		}
		if(searchEmp.getEname()!=null){
			sb.append(" and t1.ename like '%"+searchEmp.getEname()+"%'");
		}
		if(searchEmp.getDeptno()!=null){
			sb.append(" and t1.deptno ='"+searchEmp.getDeptno()+"'");
		}
		
		PreparedStatement pst=conn.prepareStatement(sb.toString());
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	//查询emp表中所有雇员的empno、ename
	public ResultSet mgrList(Connection conn)throws Exception{
		String sql="select empno,ename from emp";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public int empDelete(Connection conn,String delIds)throws Exception{
		String sql="delete from emp where empno in("+delIds+")";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	public int empAdd(Connection conn,Emp emp)throws Exception{
		String sql="insert into emp values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, emp.getEmpno());
		pstmt.setString(2, emp.getEname());
		pstmt.setString(3, emp.getJob());
		pstmt.setInt(4, emp.getMgr());
		pstmt.setString(5, DateUtil.formatDate(emp.getHiredate(), "yyyy-MM-dd"));
		pstmt.setDouble(6, emp.getSal());
		
		if(emp.getComm()!=null){
			pstmt.setDouble(7, emp.getComm());		
		}else{
			pstmt.setString(7, null);
		}
		
		pstmt.setInt(8, emp.getDeptno());
		return pstmt.executeUpdate();
	}
	
	public int empModify(Connection conn,Emp emp)throws Exception{
		String sql="update emp set ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=? where empno=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, emp.getEname());
		pstmt.setString(2, emp.getJob());
		pstmt.setInt(3, emp.getMgr());
		pstmt.setString(4, DateUtil.formatDate(emp.getHiredate(), "yyyy-MM-dd"));
		pstmt.setDouble(5, emp.getSal());
		
		if(emp.getComm()!=null){
			pstmt.setDouble(6, emp.getComm());		
		}else{
			pstmt.setString(6, null);
		}
		
		pstmt.setInt(7, emp.getDeptno());
		pstmt.setInt(8, emp.getEmpno());
		
		return pstmt.executeUpdate();
	}
	public int findMaxEmpno(Connection conn) throws SQLException{
		String sql="select max(empno) as maxEmpno from emp";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("maxEmpno");
		}else{
			return 0;
		}
		
	}
	public boolean getEmpByDeptno(Connection conn,String empno)throws Exception{
		String sql="select * from emp where deptno=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(empno));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
}
