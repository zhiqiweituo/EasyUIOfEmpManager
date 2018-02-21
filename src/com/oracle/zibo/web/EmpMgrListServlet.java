package com.oracle.zibo.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.zibo.dao.EmpDao;
import com.oracle.zibo.util.DbUtil;
import com.oracle.zibo.util.JsonUtil;
import com.oracle.zibo.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(name="EmpMgrListServlet",urlPatterns="/empMgrList")
public class EmpMgrListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DbUtil dbUtil=new DbUtil();
	private EmpDao empDao=new EmpDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			JSONArray mgrListJSONArray=JsonUtil.formatRsToJsonArray(empDao.mgrList(conn));

			JSONArray result=new JSONArray();
			
			//构造第一行（""，"请选择部门名称"）
			JSONObject jSONObject=new JSONObject();
			jSONObject.put("empno", "");
			jSONObject.put("ename", "请选择所属领导");
			jSONObject.put("selected", "true");
			result.add(jSONObject);
			
			//构造剩余真实数据
			for(int i=0;i<mgrListJSONArray.size();i++){
				jSONObject=(JSONObject)mgrListJSONArray.get(i);
				result.add(jSONObject);
			}
			
			ResponseUtil.write(resp, result);
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
