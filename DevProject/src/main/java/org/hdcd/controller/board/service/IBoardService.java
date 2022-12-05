package org.hdcd.controller.board.service;

import java.util.List;

import org.hdcd.vo.Board;

public interface IBoardService {
	public void register(Board board) throws Exception;
	public List<Board> list() throws Exception;
	public Board read(int boardNo) throws Exception;
	public void update(Board board) throws Exception;
	public void delete(int boardNo) throws Exception;
}
