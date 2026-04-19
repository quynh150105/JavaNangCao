/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quynh.quanlysinhvienhibernate.dao;

import com.quynh.quanlysinhvienhibernate.entity.Student;
import java.util.List;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class StudentDAO {
    
    private  final SessionFactory sessionFactory;   
    
    public StudentDAO(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    
    public void createStudent(Student student){
        Transaction ts = null;
        
        try(Session session = sessionFactory.openSession())
        {
         ts = session.beginTransaction();
         
         Long count = session.createQuery(
                    "Select count(s) from Student s Where s.email =:email",Long.class)
                 .setParameter("email", student.getEmail())
                 .uniqueResult();
         
         if(count != null  && count > 0){
             ts.rollback();
               throw new RuntimeException("email đã tồn tại!");
         }
         
         session.persist(student);
         
         ts.commit();
            System.out.println("Them thanh cong: " + student);
         
            
        }catch(Exception e){
            if(ts != null && ts.isActive()){
                ts.rollback();
            }
            throw e;
        } 
    }
    
    public List<Student> getAll(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Student", Student.class)
                    .getResultList();
        }
    }
    
    public Student getById(Long id){
        try(Session session = sessionFactory.openSession()){
            return session.find(Student.class, id);
        }
    }
    
    public void update(Student student){
        Transaction ts = null;
        try(Session session = sessionFactory.openSession()){
            ts = session.beginTransaction();
                       
            session.merge(student);
                        
            ts.commit();
            System.out.println("Update thanh cong");
        }catch(Exception e){
            if(ts!= null){
                ts.rollback();
            }
            System.out.println("Loi khi update");
        }
    }
    
    public void delete(Long id){
        Transaction ts = null;
        try(Session session = sessionFactory.openSession()){
            ts = session.beginTransaction();
            
            Student student = session.get(Student.class, id);
            
            if(student != null){
                session.remove(student);
                ts.commit();
                System.out.println("Xoa thanh cong id= " + id);
            }
            else{
                System.out.println("Khong tinm thay Id: " + id);
            }         
        }catch(Exception e){
            if(ts!=null && ts.isActive()){
                ts.rollback();
                System.out.println("Loi khi xoa");
            }
        }
    }
    
    public void close(){
        if(sessionFactory != null  && !sessionFactory.isClosed()){
            sessionFactory.close();
        }
    }
    
    
}
