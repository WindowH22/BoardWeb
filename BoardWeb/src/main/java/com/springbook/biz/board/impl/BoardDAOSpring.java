package com.springbook.biz.board.impl;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;


//DAO(Data Access Object)
@Repository
public class BoardDAOSpring{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// SQL ���ɾ��
	private final String BOARD_INSERT = "insert into board(seq,title, writer,content) values((select nvl(max(seq),0)+1 from board),?,?,?)"; 
	private final String BOARD_UPDATE = "update board set title=?, content=?where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	
	// CRUD ����� �޼ҵ� ����
	//	�� ���
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC�� insertBoard() ��� ó��");
		jdbcTemplate.update(BOARD_INSERT,vo.getTitle(),vo.getWriter(),vo.getContent());
	}
	
	// �� ����
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC�� updateBoard() ��� ó��");
		jdbcTemplate.update(BOARD_UPDATE,vo.getTitle(),vo.getWriter(),vo.getContent());
	}
	
	// �� ����
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC�� deleteBoard() ��� ó��");
		jdbcTemplate.update(BOARD_DELETE,vo.getTitle(),vo.getWriter(),vo.getContent());
	}	
	
	// �� �� ��ȸ
		public BoardVO getBoard(BoardVO vo) {
			System.out.println("===> JDBC�� getBoard() ��� ó��");
			Object[] args = {vo.getSeq()};
			return jdbcTemplate.queryForObject(BOARD_GET,args,new BoardRowMapper());
		}
	// �� ��� ��ȸ
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("===> JDBC�� getBoard() ��� ó��");
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}	
}

class BoardRowMapper implements RowMapper<BoardVO>{
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException{
		BoardVO board = new BoardVO();
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getDate("REGDATE"));
		board.setCnt(rs.getInt("CNT"));
		return board;
	}
}
