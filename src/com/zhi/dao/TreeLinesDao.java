package com.zhi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zhi.model.TreeLines;

public class TreeLinesDao {
	public ArrayList<TreeLines> treeLinesList(Connection conn,int parentId) throws SQLException{
		String sql="select id,text,parent_id,state,icon_cls from tree_lines where parent_id=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, parentId);
		ResultSet rs=pst.executeQuery();
		
		ArrayList<TreeLines> treeLinesList=new ArrayList<TreeLines>();
		while(rs.next()){
			TreeLines treeLines=new TreeLines();
			treeLines.setId(rs.getInt("id"));
			treeLines.setText(rs.getString("text"));
			treeLines.setParent_id(rs.getInt("parent_id"));
			treeLines.setState(rs.getString("state"));
			treeLines.setIconCls(rs.getString("icon_cls"));
			treeLinesList.add(treeLines);
		}
		return treeLinesList;
	}
}
