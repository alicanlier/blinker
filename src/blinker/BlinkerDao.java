package blinker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BlinkerDao implements IDao<Blinker,String> {

	@Override
	public int insert(Connection conn, Blinker vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO BLINKER (WORD, MEAN) "
				+ "VALUES(?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,vo.getWord());
				pstmt.setString(2, vo.getMean());
				
				int res = 0;

				try {
				res = pstmt.executeUpdate();
				} catch (SQLException e) {
				return -1;
				}
				conn.close();
				return res;
				}


	@Override
	public int delete(Connection conn, String pk) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM BLINKER WHERE WORD=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pk);
		int res = pstmt.executeUpdate();
		conn.close();	
		return res;
	}


	@Override
	public int update(Connection conn, Blinker vo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE BLINKER "
				+ "SET WORD=?, MEAN=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(2, vo.getWord());
		pstmt.setString(3, vo.getMean());
				
		int res = pstmt.executeUpdate();
		conn.close();
		return res;
	}

	@Override
	public Blinker select(Connection conn, String pk) throws SQLException {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM BLINKER WHERE WORD=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pk);
		ResultSet rs =  pstmt.executeQuery();
		Blinker vo = new Blinker();
		while(rs.next()) {
			
		vo.setWord(rs.getString("WORD"));
		}
		
		conn.close();
		return vo;
	}

	@Override
	public List<Blinker> selectAll(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM BLINKER";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Blinker> data = new ArrayList<>();
		while(rs.next()) {
			Blinker vo = new Blinker();
		vo.setWord(rs.getString("WORD"));
		vo.setMean(rs.getString("MEAN"));
		data.add(vo);
		}
		conn.close();
		return data;
	}



	}


