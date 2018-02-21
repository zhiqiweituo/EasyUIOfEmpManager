package com.zhi.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhi.dao.DeptDao;
import com.zhi.util.DbUtil;
import com.zhi.util.JsonUtil;
import com.zhi.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(name="DeptComboListServlet",urlPatterns="/deptComboList")
public class DeptComboListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DbUtil dbUtil=new DbUtil();
	private DeptDao deptDao=new DeptDao();
	
	@Override //此处只提供一个doGet方法，前台需要Ajax的get请求（最好doGet和doPost都提供）
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			JSONArray jSONArray=JsonUtil.formatRsToJsonArray(deptDao.deptList(conn, null, null));
			
			JSONArray result=new JSONArray();
			
			//构造第一行（""，"请选择部门名称"）
			JSONObject jSONObject=new JSONObject();
			jSONObject.put("deptno", "");
			jSONObject.put("dname", "请选择部门名称");
			jSONObject.put("selected", "true");
			result.add(jSONObject);
			
			//构造剩余真实数据
			for(int i=0;i<jSONArray.size();i++){
				jSONObject=(JSONObject)jSONArray.get(i);
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
