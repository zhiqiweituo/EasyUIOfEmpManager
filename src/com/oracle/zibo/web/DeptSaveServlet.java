package com.oracle.zibo.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.zibo.dao.DeptDao;
import com.oracle.zibo.model.Dept;
import com.oracle.zibo.util.DbUtil;
import com.oracle.zibo.util.ResponseUtil;
import com.oracle.zibo.util.StringUtil;

import net.sf.json.JSONObject;

@WebServlet(name="DeptSaveServlet",urlPatterns="/deptSave")
public class DeptSaveServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DbUtil dbUtil=new DbUtil();
	private DeptDao deptDao=new DeptDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String dname=request.getParameter("dname");
		String loc=request.getParameter("loc");
		Dept dept=new Dept(dname,loc);

		String deptno=request.getParameter("deptno");
		if(StringUtil.isNotEmpty(deptno)){
			dept.setDeptno(Integer.parseInt(deptno));
		}
		
		Connection conn=null;
		try{
			conn=dbUtil.getConn();
			int saveNums=0;
			if(StringUtil.isNotEmpty(deptno)){ //修改
				saveNums=deptDao.deptModify(conn, dept);
			}else{ //添加
				dept.setDeptno(deptDao.findMaxDeptno(conn)+10); //最大deptno+10
				saveNums=deptDao.deptAdd(conn, dept); //添加
			}

			JSONObject result=new JSONObject();
			if(saveNums>0){
				result.put("flag", "true");
				result.put("msg", "保存成功");
			}else{
				result.put("flag", "false");
				result.put("msg", "保存失败");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
