/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.util.Iterator;
import java.util.Properties;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.id.Configurable;
import org.hibernate.type.Type;

/**
 *
 * @author chandana_l
 */
public class IdGenerator implements IdentifierGenerator , Configurable{
    String coulmnName="";
    @Override
    public Serializable generate(SessionImplementor si, Object o) throws HibernateException {
        Session session = null;
        session = HibernateInit.sessionFactory.openSession();

        try {
            int count=0;
            String sql="select max("+coulmnName+") as Id from "+o.getClass().getSimpleName();
            Query query = session.createQuery(sql);
            Iterator itCount = query.iterate();
            if(itCount.hasNext())count = (int)itCount.next();
            int id=count+1;
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (session != null) {
                session.close();
                session = null;
            }

        }
        return 1;
    }

    @Override
    public void configure(Type type, Properties prprts, Dialect dlct) throws MappingException {
        coulmnName=prprts.getProperty("columnPram");
    }    
}
