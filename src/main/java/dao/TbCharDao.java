package dao;

import dto.TbChar;

import java.sql.*;
import java.util.ArrayList;

public class TbCharDao {

    // 생성자
    // 변수
    // 메소드
    // 디비 연결 메소드
    public Connection dbConn() {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "mydb";
        final String DB_USER = "root";
        final String DB_PASS = "1234";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
        return conn;
    }

    // 데이터 넣기 메소드
    public void insert(TbChar tbchar) {
        //쿼리문 준비
        String sql = "INSERT INTO `tb_character` (`name`, `hp`, `job`) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = dbConn();   // db 연결 메소드
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tbchar.getName());
            pstmt.setInt(2, tbchar.getHp());
            pstmt.setString(3, tbchar.getJob());

            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("데이터 삽입 성공!");
            }

        } catch (Exception e) {
            System.out.println("데이터 삽입 실패!");
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void update(int hp, int id) {
        //쿼리문 준비
        String sql = "UPDATE `tb_character` SET `hp`= ? WHERE  `id`= ?";
        PreparedStatement pstmt = null;
        Connection conn = dbConn();   // db 연결 메소드
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, hp);
            pstmt.setInt(2, id);

            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("데이터 삽입 성공!");
            }

        } catch (Exception e) {
            System.out.println("데이터 삽입 실패!");
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    // 데이터 보기 메소드드
    public ArrayList<TbChar> select() {
        ArrayList<TbChar> list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn();   // db 연결 메소드
        try {
            String sql = "select * from tb_character";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TbChar dto = new TbChar();
                dto.setName(rs.getString("name"));
                dto.setHp(rs.getInt("hp"));
                dto.setJob(rs.getString("job"));
                list.add(dto);
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;

    }
    public TbChar select(String name) {
        TbChar dto = new TbChar();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn();   // db 연결 메소드
        try {
            String sql = "select * from tb_character where name = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setHp(rs.getInt("hp"));
                dto.setJob(rs.getString("job"));
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dto;

    }
}
