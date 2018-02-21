package com.oracle.zibo.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.zibo.dao.EmpDao;
import com.oracle.zibo.model.Emp;
import com.oracle.zibo.model.PageBean;
import com.oracle.zibo.util.DbUtil;
import com.oracle.zibo.util.JsonUtil;
import com.oracle.zibo.util.ResponseUtil;
import com.oracle.zibo.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(name="EmpListServlet",urlPatterns="/empList")
public class EmpListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DbUtil dbUtil=new DbUtil();
	private EmpDao empDao=new EmpDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		//����������,����ǰ̨��������
		String s_empno=request.getParameter("s_empno");
		String s_ename=request.getParameter("s_ename");
		String s_deptno=request.getParameter("s_deptno");
		
		Emp searchEmp=new Emp();
		//�ж��Ƿ�Ϊ��,��Ϊ������searchEmp����
		if(StringUtil.isNotEmpty(s_empno)){
			searchEmp.setEmpno(Integer.parseInt(s_empno));
		}
		if(StringUtil.isNotEmpty(s_ename)){
			searchEmp.setEname(s_ename);
		}
		if(StringUtil.isNotEmpty(s_deptno)){
			searchEmp.setDeptno(Integer.parseInt(s_deptno));
		}
		
		Connection con=null;
		try{
			con=dbUtil.getConn();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(empDao.empList(con, pageBean,searchEmp));
			int total=empDao.empCount(con,searchEmp);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConn(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
