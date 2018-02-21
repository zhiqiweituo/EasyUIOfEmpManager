package com.zhi.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhi.dao.EmpDao;
import com.zhi.model.Emp;
import com.zhi.model.PageBean;
import com.zhi.util.DbUtil;
import com.zhi.util.JsonUtil;
import com.zhi.util.ResponseUtil;
import com.zhi.util.StringUtil;

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
		
		//多条件搜搜,接受前台搜索条件
		String s_empno=request.getParameter("s_empno");
		String s_ename=request.getParameter("s_ename");
		String s_deptno=request.getParameter("s_deptno");
		
		Emp searchEmp=new Emp();
		//判断是否为空,不为空塞入searchEmp对象
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
