package org.hnylj.web;  
  
import java.util.List;  
  
import org.hnylj.util.PageDAO;  
import org.hnylj.util.Student;  
  
import com.opensymphony.xwork2.ActionSupport;  
  
public class ShowAction extends ActionSupport {  
      
    private List<Student> students ;  
    private int pageNow = 1 ; //初始化为1,默认从第一页开始显示  
    private int pageSize = 3 ; //每页显示5条记录  
      
    private PageDAO pageDAO = new PageDAO () ;  
      
    public List<Student> getStudents() {  
        return students;  
    }  
  
    public void setStudents(List<Student> students) {  
        this.students = students;  
    }  
      
    public int getPageNow() {  
        return pageNow;  
    }  
  
    public void setPageNow(int pageNow) {  
        this.pageNow = pageNow;  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public String execute() throws Exception {  
        students = pageDAO.queryByPage(pageSize, pageNow) ;  
        return SUCCESS ;  
    }  
}  
