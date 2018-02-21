package com.zhi.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhi.dao.TreeLinesDao;
import com.zhi.model.TreeLines;
import com.zhi.util.DbUtil;
import com.zhi.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(name="TreeLinesServlet",urlPatterns="/treeLines")
public class TreeLinesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil=new DbUtil();
	TreeLinesDao treeLinesDao=new TreeLinesDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Connection conn=null;
		try {
			conn=dbUtil.getConn(); //获取数据库连接conn
			ArrayList<TreeLines> treeLinesList=treeLinesDao.treeLinesList(conn, 0);
			
			JSONArray jSONArray=listMenu(conn,treeLinesList); //返回树形菜单
			
			try {
				ResponseUtil.write(resp, jSONArray);
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
	private JSONArray listMenu(Connection conn,ArrayList<TreeLines> treeLinesList) throws SQLException{
		JSONArray result=new JSONArray();
		for(TreeLines el:treeLinesList){
			JSONObject obj=new JSONObject();
			ArrayList<TreeLines> temp=treeLinesDao.treeLinesList(conn, el.getId());
			if(temp.size()>0){
				obj.put("id", el.getId());
				obj.put("text", el.getText());
				obj.put("state", el.getState());
				obj.put("iconCls", el.getIconCls());
				JSONArray JSONArray=listMenu(conn,temp);
				obj.put("children", JSONArray);
			}else{
				obj.put("id", el.getId());
				obj.put("text", el.getText());
				obj.put("state", el.getState());
				obj.put("iconCls", el.getIconCls());
			}
			result.add(obj);
		}
		return result;
	}
	
	
	
	
	
	
	
//	private JSONArray listMenu(Connection conn,ArrayList<TreeLines> treeLinesList) throws SQLException{
//		JSONArray result=new JSONArray();
//		for(TreeLines treeLines:treeLinesList){
//			if(treeLinesDao.treeLinesList(conn, treeLines.getId()).size()>0){
//				jSONObject.put("id", treeLines.getId());
//				jSONObject.put("text", treeLines.getText());
//				
//				//递归调用
//				jSONObject.put("children", listMenu(conn,treeLinesDao.treeLinesList(conn, treeLines.getId())));
//				
//			}else{
//				jSONObject.put("id", treeLines.getId());
//				jSONObject.put("text", treeLines.getText());
//			}
//			result.add(jSONObject);
//		}
//		return result;
//	}
}
