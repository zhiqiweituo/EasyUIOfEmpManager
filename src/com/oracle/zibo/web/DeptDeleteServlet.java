package com.oracle.zibo.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.zibo.dao.DeptDao;
import com.oracle.zibo.dao.EmpDao;
import com.oracle.zibo.util.DbUtil;
import com.oracle.zibo.util.ResponseUtil;

import net.sf.json.JSONObject;

@WebServlet(name="DeptDeleteServlet",urlPatterns="/deptDelete")
public class DeptDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DbUtil dbUtil=new DbUtil();
	private DeptDao deptDao=new DeptDao();
	private EmpDao empDao=new EmpDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delIds=request.getParameter("delIds");

		Connection con=null;
		try{
			con=dbUtil.getConn();
			JSONObject result=new JSONObject();
			String str[]=delIds.split(",");
			for(int i=0;i<str.length;i++){
				boolean f=empDao.getEmpByDeptno(con, str[i]);
				if(f){
					result.put("errorIndex", i);
					result.put("errorMsg", "���������й�Ա������ɾ����");
					ResponseUtil.write(response, result);
					return;
				}
			}
			int delNums=deptDao.deptDelete(con, delIds);
			if(delNums>0){
				result.put("flag", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "ɾ��ʧ��");
			}
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
