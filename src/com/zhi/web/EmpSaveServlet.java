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
import com.zhi.util.DateUtil;
import com.zhi.util.DbUtil;
import com.zhi.util.ResponseUtil;
import com.zhi.util.StringUtil;

import net.sf.json.JSONObject;

@WebServlet(name="EmpSaveServlet",urlPatterns="/empSave")
public class EmpSaveServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DbUtil dbUtil=new DbUtil();
	private EmpDao empDao=new EmpDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String empno=request.getParameter("empno");
		String ename=request.getParameter("ename");
		String job=request.getParameter("job");
		String mgr=request.getParameter("mgr");
		String hiredate=request.getParameter("hiredate");
		String sal=request.getParameter("sal");
		String comm=request.getParameter("comm");
		String deptno=request.getParameter("deptno");
				
		Emp emp=new Emp();
		if(empno!=null){
			emp.setEmpno(Integer.parseInt(empno));
		}
		emp.setEname(ename);
		emp.setJob(job);
		emp.setMgr(Integer.parseInt(mgr));
		try {
			emp.setHiredate(DateUtil.formatString(hiredate, "MM/dd/yyyy"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		emp.setSal(Double.parseDouble(sal));
		
		//处理没有佣金的雇员情况
		if(StringUtil.isNotEmpty(comm)){ //java.lang.NumberFormatException: empty String
			emp.setComm(Double.parseDouble(comm));			
		}
		
		emp.setDeptno(Integer.parseInt(deptno));


		Connection conn=null;
		try{
			conn=dbUtil.getConn();
			int saveNums=0;
			JSONObject result=new JSONObject();

			if(empno==null){ //添加
				//增加雇员，查出系统最大编号，然后+1
				//并且，考虑并发访问时，应该加线程锁（暂时不考虑，1年工作经验之后再考虑）
				int maxEmpno=empDao.findMaxEmpno(conn);
				emp.setEmpno(maxEmpno+1);
				saveNums=empDao.empAdd(conn, emp);
			}else{ //修改
				saveNums=empDao.empModify(conn, emp);
			}

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
