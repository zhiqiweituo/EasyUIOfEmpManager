package com.oracle.zibo.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.zibo.dao.DeptDao;
import com.oracle.zibo.model.Dept;
import com.oracle.zibo.model.PageBean;
import com.oracle.zibo.util.DbUtil;
import com.oracle.zibo.util.JsonUtil;
import com.oracle.zibo.util.ResponseUtil;
import com.oracle.zibo.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(name="DeptListServlet",urlPatterns="/deptList")
public class DeptListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil=new DbUtil();
	DeptDao deptDao=new DeptDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		ArrayList<Dept> deptList=new ArrayList<Dept>();
//		Dept d1=new Dept(10,"�з���","����");
//		Dept d2=new Dept(20,"���۲�","�ൺ");
//		Dept d3=new Dept(30,"���̲�","�Ͳ�");
//		Dept d4=new Dept(40,"���ڲ�","Ϋ��");
//		deptList.add(d1);
//		deptList.add(d2);
//		deptList.add(d3);
//		deptList.add(d4);
//		//����ģ�����ݿ��ѯ�����صĲ����б�
//		
//		JSONArray jSONArray=new JSONArray();
//		for(Dept d:deptList){
//			JSONObject jSONObject=new JSONObject();
//			jSONObject.put("deptno", d.getDeptno());
//			jSONObject.put("dname", d.getDname());
//			jSONObject.put("loc", d.getLoc());
//			jSONArray.add(jSONObject);
//		}
//		
//		JSONObject result=new JSONObject();
//		result.put("rows", jSONArray); //easyUI�涨������rows
//		result.put("total", deptList.size()); //easyUI�涨������total
//		try {
//			ResponseUtil.write(resp, result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String page=req.getParameter("page"); //easyUI�涨������page
		String rows=req.getParameter("rows"); //easyUI�涨������rows
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		String s_dname=req.getParameter("s_dname");
		String s_loc=req.getParameter("s_loc");
		Dept dept=new Dept();
		
		if(StringUtil.isNotEmpty(s_dname)){
			dept.setDname(s_dname);
		}
		if(StringUtil.isNotEmpty(s_loc)){
			dept.setLoc(s_loc);
		}
		
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			
			JSONArray deptJSONArray=JsonUtil.formatRsToJsonArray(deptDao.deptList(conn, pageBean, dept));
			int total=deptDao.deptCount(conn, dept);
			
			JSONObject result=new JSONObject();
			result.put("rows", deptJSONArray); //easyUI�涨������rows
			result.put("total", total); //easyUI�涨������total
			
			try {
				ResponseUtil.write(resp, result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
