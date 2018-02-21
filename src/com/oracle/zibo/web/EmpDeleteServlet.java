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
import com.oracle.zibo.util.ResponseUtil;

import net.sf.json.JSONObject;

@WebServlet(name="EmpDeleteServlet",urlPatterns="/empDelete")
public class EmpDeleteServlet extends HttpServlet {

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
		String delIds=req.getParameter("delIds");
		
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			
			JSONObject result=new JSONObject();
			int delNums=empDao.empDelete(conn, delIds);
			if(delNums>0){
				result.put("flag", "true");
				result.put("delNums", delNums);
			}else{
				result.put("flag", "false");
				result.put("msg", "ɾ��ʧ��");
			}
			ResponseUtil.write(resp, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
