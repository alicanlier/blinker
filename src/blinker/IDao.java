package blinker;

import java.sql.*;
import java.util.*;

// V - VO class type,P primary key type
public interface IDao<V,P> { 
public int insert(Connection conn,V vo) throws SQLException;
public int delete(Connection conn,P pk) throws SQLException;
public int update(Connection conn,V vo) throws SQLException;
public V select(Connection conn,P pk) throws SQLException;
public List<V> selectAll(Connection conn) throws SQLException;


}