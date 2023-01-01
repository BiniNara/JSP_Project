package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//mysql 접속하게해주는 부분
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword ="k404";	
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int login(String userID, String userPassword) {
		//ID,Password 가 실제로 존재하는지 확인
		String SQL = "SELECT userPassword FROM USER WHERE userID =?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			//아이디가 있을때
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공했을때
				}
				else
					return 0; // 비밀번호 틀리면
			}
			return -1; //아이디가 없을때
		} catch (Exception e) {
			//예외 발생시 해당 예외를 출력
			e.printStackTrace();
		}
		return -2; //데이터베이스 오류
		}
	
}
