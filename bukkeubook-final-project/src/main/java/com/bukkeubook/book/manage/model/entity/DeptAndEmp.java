package com.bukkeubook.book.manage.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/* TBL_DEPT 테이블에 매칭될 Dept 엔티티 클래스도 만들어 보기 */
@Entity
@Table(name = "TBL_DEPT")
public class DeptAndEmp implements Serializable {

   private static final long serialVersionUID = 541840903128253305L;

   /* DB 자료형 */
   
//   DEPT_CODE      NUMBER            부서코드
//   DEPT_NAME      NVARCHAR2(31 CHAR)   부서명
//   DEPT_REP_PHONE   VARCHAR2(15 BYTE)   대표번호
   
   @Id
   @Column(name = "DEPT_CODE")   // PK
   private int deptCode;
   
   @Column(name = "DEPT_NAME")
   private String deptName;
   
   @Column(name = "DEPT_REP_PHONE")
   private String deptRepPhone;
   
   @OneToMany(mappedBy ="dept")   // FK명
   private List<EmpAndDept> empList = new ArrayList<>();

   public DeptAndEmp() {
      super();
   }

   public DeptAndEmp(int deptCode, String deptName, String deptRepPhone, List<EmpAndDept> empList) {
      super();
      this.deptCode = deptCode;
      this.deptName = deptName;
      this.deptRepPhone = deptRepPhone;
      this.empList = empList;
   }

   public int getDeptCode() {
      return deptCode;
   }

   public void setDeptCode(int deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptName() {
      return deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptRepPhone() {
      return deptRepPhone;
   }

   public void setDeptRepPhone(String deptRepPhone) {
      this.deptRepPhone = deptRepPhone;
   }

   public List<EmpAndDept> getEmpList() {
      return empList;
   }

   public void setEmpList(List<EmpAndDept> empList) {
      this.empList = empList;
   }

   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   @Override
   public String toString() {
      return "DeptAndEmp [deptCode=" + deptCode + ", deptName=" + deptName + ", deptRepPhone=" + deptRepPhone
            + ", empList=" + empList + "]";
   }

}