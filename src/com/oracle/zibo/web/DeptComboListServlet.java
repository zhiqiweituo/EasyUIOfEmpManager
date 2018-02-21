package com.oracle.zibo.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.zibo.dao.DeptDao;
import com.oracle.zibo.util.DbUtil;
import com.oracle.zibo.util.JsonUtil;
import com.oracle.zibo.util.ResponseUtil;

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
	
	@Override //�˴�ֻ�ṩһ��doGet������ǰ̨��ҪAjax��get�������doGet��doPost���ṩ��
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			JSONArray jSONArray=JsonUtil.formatRsToJsonArray(deptDao.deptList(conn, null, null));
			
			JSONArray result=new JSONArray();
			
			//�����һ�У�""��"��ѡ��������"��
			JSONObject jSONObject=new JSONObject();
			jSONObject.put("deptno", "");
			jSONObject.put("dname", "��ѡ��������");
			jSONObject.put("selected", "true");
			result.add(jSONObject);
			
			//����ʣ����ʵ����
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
